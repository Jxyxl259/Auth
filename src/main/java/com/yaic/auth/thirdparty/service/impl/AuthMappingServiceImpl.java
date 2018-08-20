package com.yaic.auth.thirdparty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.thirdparty.dao.AuthMappingDao;
import com.yaic.auth.thirdparty.dao.DataSourceDao;
import com.yaic.auth.thirdparty.dao.ProjectDao;
import com.yaic.auth.thirdparty.dto.AuthMappingDto;
import com.yaic.auth.thirdparty.dto.InterfaceInfoDto;
import com.yaic.auth.thirdparty.model.AuthMappingModel;
import com.yaic.auth.thirdparty.service.AuthMappingService;

@Service
public class AuthMappingServiceImpl implements AuthMappingService {
	// private static final Logger logger =
	// LoggerFactory.getLogger(AuthMappingServiceImpl.class);
	@Autowired
	private AuthMappingDao authMappingMapper;
	@Autowired
	private ProjectDao projectMapper;
	@Autowired
	private DataSourceDao datasourceMapper;

	@Override
	public List<AuthMappingModel> getList(AuthMappingModel authMappingModel) throws Exception {
		List<AuthMappingModel> list = authMappingMapper.selectByConditions(authMappingModel);
		return list;
	}

	@Override
	public Integer addInfo(AuthMappingModel model) throws Exception {
//		model.setCreatedUser(Constants.LOGIN_USER_CODE());
//		model.setUpdatedUser(Constants.LOGIN_USER_CODE());
		model.setValidFlag(PublicParamters.VALID_FLAG_Y);
		return authMappingMapper.insertSelective(model);
	}

	@Override
	public Integer updateInfo(AuthMappingModel authMappingModel) throws Exception {
//		authMappingModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		return authMappingMapper.updateByPrimaryKeySelective(authMappingModel);
	}

	@Override
	public Integer deleteInfo(Integer mappingId) throws Exception {
		return authMappingMapper.deleteByPrimaryKey(mappingId);
	}

	@Override
	public AuthMappingModel getOneByMappingId(Integer mappingId) throws Exception {
		return authMappingMapper.selectByPrimaryKey(mappingId);
	}

	@Override
	public AuthMappingModel getOneByAuthMapping(AuthMappingModel authMappingModel) throws Exception {
		List<AuthMappingModel> list = authMappingMapper.selectByConditions(authMappingModel);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AuthMappingModel> getAuthMappListByReqUrlAndAppId(String appId, String requestUrl) throws Exception {
		return authMappingMapper.selectByAppIdAndReqUrl(appId, requestUrl);
	}

	@Override
	public AuthMappingModel getOneByProjectIdAndServerId(Integer projectId, Integer serverId) throws Exception {
		return authMappingMapper.selectOneByProjectIdAndServerId(projectId, serverId);
	}

	@Override
	public AuthMappingModel getOneByRequestTypeAndRequestUrl(String requestType, String requestUrl) throws Exception {
		return authMappingMapper.selectOneByRequestTypeAndRequestUrl(requestType, requestUrl);
	}

	@Override
	public List<InterfaceInfoDto> selectListsByAppid(String appId) throws Exception {
		return authMappingMapper.selectListsByAppid(appId);
	}

	@Override
	public List<AuthMappingDto> getMappAndServerByProId(Integer projectId) throws Exception {
		return authMappingMapper.getMappAndServerByProId(projectId);
	}

	@Override
	public Integer deleteByProjectId(Integer projectId) throws Exception {
		return authMappingMapper.deleteByProjectId(projectId);
	}

	@Override
	public String judgeAuthMappInfo(AuthMappingModel model) throws Exception {
		
//		Integer datasourceId = 0;
//		
//		//接口类型为 project时,页面参数默认为空,需手动设置为project
//		if(StringUtils.isBlank(model.getRequestType()))
//			model.setRequestType(PublicParamters.REQUEST_TYPE_PROJECT);
//		
//		AuthMappingDto dto = new AuthMappingDto(model);
//		List<AuthMappingDto> lists = new ArrayList<AuthMappingDto>();
//		lists.add(dto);
//		
//		ProjectModel projectModel = projectMapper.selectOneByPrimaryKey(model.getProjectId());
//		datasourceId = projectModel.getDataSourceId();
//		
//		DataSourceModel sourceModel = datasourceMapper.selectOneByPrimaryKey(datasourceId);
//		
//		String result = configService.judgeRequestUrl(lists, sourceModel.getAppId(), datasourceId,model.getProjectId(),model.getReqType());
		String result = "";
		return result;
	}

	@Override
	public List<Map<String,Object>> selectByProjectDataSource(Map<String,String> paramMap) {
		return authMappingMapper.selectByProjectDataSource(paramMap);
	}

	@Override
	public List<Map<String,Object>> selectByProjectDataSourceOriginal(Map<String,String> paramMap) {
		return authMappingMapper.selectByProjectDataSourceOriginal(paramMap);
	}

    @Override
    public List<AuthMappingModel> getMappingByAppId(String appid) {
        return authMappingMapper.getMappingByAppId(appid);
    }

}
