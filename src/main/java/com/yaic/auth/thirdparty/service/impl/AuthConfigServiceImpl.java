package com.yaic.auth.thirdparty.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.thirdparty.dto.AuthBasicInfo;
import com.yaic.auth.thirdparty.dto.AuthEncryptDto;
import com.yaic.auth.thirdparty.dto.AuthMappingDto;
import com.yaic.auth.thirdparty.dto.CallbackUrlDto;
import com.yaic.auth.thirdparty.dto.DataSourceDto;
import com.yaic.auth.thirdparty.dto.ProjectDto;
import com.yaic.auth.thirdparty.model.AccountModel;
import com.yaic.auth.thirdparty.model.AuthEncryptModel;
import com.yaic.auth.thirdparty.model.CallbackUrlModel;
import com.yaic.auth.thirdparty.model.DataSourceModel;
import com.yaic.auth.thirdparty.model.ProjectModel;
import com.yaic.auth.thirdparty.service.AccountService;
import com.yaic.auth.thirdparty.service.AuthConfigService;
import com.yaic.auth.thirdparty.service.AuthEncryptService;
import com.yaic.auth.thirdparty.service.AuthMappingService;
import com.yaic.auth.thirdparty.service.CallbackUrlService;
import com.yaic.auth.thirdparty.service.DataSourceService;
import com.yaic.auth.thirdparty.service.ProjectService;

@Service
public class AuthConfigServiceImpl implements AuthConfigService {

	private static final Logger logger = LoggerFactory.getLogger(AuthConfigServiceImpl.class);
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	DataSourceService dataSourceService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	AuthMappingService authMappService;
	
	@Autowired
	CallbackUrlService callbackUrlService;
	
	@Autowired
	AuthEncryptService authEncryptService;
	
	@Override
	public List<AccountModel> getAccountTrees() throws Exception {
		List<AccountModel> lists = accountService.getList(new AccountModel());
		return lists;
	}
	
	@Override
	public List<DataSourceModel> getDatasourceTrees(String deviceCode) throws Exception{
		
		//根据accountId获取appId
		AccountModel model = accountService.getOneByAccountId(Integer.parseInt(deviceCode));
		
		DataSourceModel sourceModel = new DataSourceModel();
		sourceModel.setAppId(model.getAppId());
		
		List<DataSourceModel> lists = dataSourceService.getList(sourceModel);
		return lists;
	}
	
	@Override
	public List<ProjectModel> getProjectTrees(String datasourceId) throws Exception {
		ProjectModel model = new ProjectModel();
		model.setDataSourceId(Integer.parseInt(datasourceId));
		model.setIsDefault(PublicParamters.DEFAULT_PROJECT_N);
		List<ProjectModel> lists = projectService.getList(model);
		return lists;
	}

	@Override
	public ProjectDto getFullProjectInfo(ProjectModel model) throws Exception {
		
		logger.debug("根据方案编号:{}获取方案信息以及其关联的所有信息",model.getProjectId());
		model = projectService.getOneByProjectId(model.getProjectId());
		
		if(model == null || model.getProjectId() == null){
			return null;
		}
		
		ProjectDto dto = new ProjectDto(model);
		
		//根据projectId获取对应方案与服务关系集合
		List<AuthMappingDto> authmappList = authMappService.getMappAndServerByProId(model.getProjectId());
		dto.setAuthmappList(authmappList);
		
		//根据projectId获取对应回调集合
		CallbackUrlModel callbackUrlModel = new CallbackUrlModel();
		callbackUrlModel.setProjectId(model.getProjectId());
		List<CallbackUrlModel> callbackUrlList = callbackUrlService.getList(callbackUrlModel);
		
		if(callbackUrlList.size() > 0){
			
			List<CallbackUrlDto> dtoList = new ArrayList<CallbackUrlDto>();
			CallbackUrlDto callbackDto = null;
			
			for (CallbackUrlModel urlModel : callbackUrlList) {
			
				callbackDto = new CallbackUrlDto(urlModel);
				
				dtoList.add(callbackDto);
			}
			
			dto.setCallbackList(dtoList);
		}
		
		
		//根据projectId获取对应权限配置信息
		AuthEncryptModel authEncryptModel = authEncryptService.getOneByAuthId(model.getAuthId());
		AuthEncryptDto authEncryptDto = new AuthEncryptDto(authEncryptModel);
		dto.setAuthEncryptDto(authEncryptDto);
		
		
		return dto;
	}

	@Override
	public DataSourceDto getFullDatasourceInfo(DataSourceModel model)  throws Exception{
		
		model = dataSourceService.getOneByDataSourceId(model.getDataSourceId());
		
		if(model == null || model.getDataSourceId() == null){
			return null;
		}
		DataSourceDto dto = new DataSourceDto(model);
		
		//获取默认方案编号
		ProjectModel projectModel = projectService.getDefaultProjectBySourceid(model.getDataSourceId());
		
		if(projectModel != null && projectModel.getProjectId() != null){
			
			List<ProjectDto> lists = new ArrayList<ProjectDto>();
			
			ProjectDto projectDto = this.getFullProjectInfo(projectModel);
			lists.add(projectDto);
			
			dto.setProjectList(lists);
		}
		
		return dto;
	}
	
	@Override
	public Integer saveDatasourProject(AuthBasicInfo basicInfo) throws Exception {
		
		Integer result = 0;
		AuthEncryptModel model2 = new AuthEncryptModel();
		
		String loginUserCode = "";//Constants.LOGIN_USER_CODE();
		//保存渠道基本信息
		if(basicInfo.getAddRole().equals("datasource")){
			
			AccountModel accoModel = accountService.getOneByAccountId(basicInfo.getPrimaryId());
			
			DataSourceModel model = new DataSourceModel();
			model.setAppId(accoModel.getAppId());
			model.setDataSource(basicInfo.getDataSource());
			model.setSourceName(basicInfo.getSourceName());
			
			model.setCreatedUser(loginUserCode);
			model.setUpdatedUser(loginUserCode);
			result = dataSourceService.addInfo(model);
			
			if(result > 0){
				
				model2.setAuthType(basicInfo.getAuthType());
				model2.setAuthParam1(basicInfo.getAuthParam1());
				model2.setEncryptType(basicInfo.getEncryptType());
				model2.setEncryptParam1(basicInfo.getEncryptParam1());
				model2.setCreatedUser(loginUserCode);
				model2.setUpdatedUser(loginUserCode);
				result = authEncryptService.addInfo(model2);
			}
			
			if(result > 0){
				ProjectModel model1 = new ProjectModel();
				model1.setDataSourceId(model.getDataSourceId());
				model1.setAuthId(model2.getAuthId());
				model1.setProjectCode(basicInfo.getDataSource()+"default");
				model1.setProjectName(basicInfo.getSourceName()+"default");
				model1.setIsDefault(PublicParamters.DEFAULT_PROJECT_Y);
				
				model1.setCreatedUser(loginUserCode);
				model1.setUpdatedUser(loginUserCode);
				result = projectService.addInfo(model1);
			}
			
		}else if(basicInfo.getAddRole().equals("project")){
			
			model2.setAuthType(basicInfo.getAuthType());
			model2.setAuthParam1(basicInfo.getAuthParam1());
			model2.setEncryptType(basicInfo.getEncryptType());
			model2.setEncryptParam1(basicInfo.getEncryptParam1());
			model2.setCreatedUser(loginUserCode);
			model2.setUpdatedUser(loginUserCode);
			result = authEncryptService.addInfo(model2);
			
			if(result > 0){
				
				//保存方案基本信息
				ProjectModel model = new ProjectModel();
				model.setDataSourceId(basicInfo.getPrimaryId());
				model.setAuthId(model2.getAuthId());
				model.setDataSourceId(basicInfo.getPrimaryId());
				model.setProjectCode(basicInfo.getProjectCode());
				model.setProjectName(basicInfo.getProjectName());
				model.setIsDefault(PublicParamters.DEFAULT_PROJECT_N);
				
				model.setCreatedUser(loginUserCode);
				model.setUpdatedUser(loginUserCode);
				result = projectService.addInfo(model);
			}
		} 
		
		return result;
	}

	@Override
	public List<String> interfaceTypesSustom() {
		List<String> lists = new ArrayList<>();
		lists.add(PublicParamters.REQUEST_TYPE_DATA_SOURCE);
		lists.add(PublicParamters.REQUEST_TYPE_HISTORY_COMMON);
		lists.add(PublicParamters.REQUEST_TYPE_HISTORY_ENCRYPT);

		return lists;
	}

}
