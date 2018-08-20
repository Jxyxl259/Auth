package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.dto.AuthBasicInfo;
import com.yaic.auth.thirdparty.dto.DataSourceDto;
import com.yaic.auth.thirdparty.dto.ProjectDto;
import com.yaic.auth.thirdparty.model.AccountModel;
import com.yaic.auth.thirdparty.model.DataSourceModel;
import com.yaic.auth.thirdparty.model.ProjectModel;

/** 
* @ClassName: AuthConfigService 
* @Description: 配置管理对应接口
* @author 
* @date 2018年6月22日 上午10:45:33 
*  
*/
public interface AuthConfigService {


	/**
	 * @return  
	* @Title: getFullProjectInfo 
	* @Description: 获取方案以及方案下的所有相关信息
	* @param model    
	* @return void  
	* @throws 
	*/
	public ProjectDto getFullProjectInfo(ProjectModel model) throws Exception;

	
	/** 
	* @Title: getFullDatasourceInfo 
	* @Description: 获取渠道以及渠道下的所有相关信息
	* @param model
	* @return    
	* @return ProjectDto  
	* @throws 
	*/
	public DataSourceDto getFullDatasourceInfo(DataSourceModel model) throws Exception;
	
	
	public List<AccountModel> getAccountTrees() throws Exception;

	public List<DataSourceModel> getDatasourceTrees(String deviceCode) throws Exception;

	public List<ProjectModel> getProjectTrees(String datasourceId) throws Exception;


	/** 
	* @Title: saveDatasourProject 
	* @Description: 新增保存渠道或方案以及鉴权信息
	* @param basicInfo
	* @return    
	* @return Integer  
	* @throws 
	*/
	Integer saveDatasourProject(AuthBasicInfo basicInfo) throws Exception;


	/** 
	* @Title: interfaceTypesSustom 
	* @Description: 获取渠道对应的特定接口类型
	* @return    
	* @return List<String>  
	* @throws 
	*/
	public List<String> interfaceTypesSustom();
			


}
