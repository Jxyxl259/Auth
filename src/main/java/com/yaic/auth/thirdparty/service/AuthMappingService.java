package com.yaic.auth.thirdparty.service;

import java.util.List;
import java.util.Map;

import com.yaic.auth.thirdparty.dto.AuthMappingDto;
import com.yaic.auth.thirdparty.dto.InterfaceInfoDto;
import com.yaic.auth.thirdparty.model.AuthMappingModel;


/** 
* @ClassName: AuthMappingService 
* @Description: 方案和服务关系接口
* @author 
* @date 2018年6月17日 下午9:58:15 
*  
*/
public interface AuthMappingService {

	/** 
	* @Title: addInfo 
	* @Description: 添加AuthMappingModel
	* @param authMappingModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer addInfo(AuthMappingModel authMappingModel) throws Exception;

	/** 
	* @Title: getList 
	* @Description: 根据authMappingModel得到AuthMappingModel集合
	* @param authMappingModel
	* @return    
	* @return List<AuthMappingModel>  
	* @throws 
	*/
	public List<AuthMappingModel> getList(AuthMappingModel authMappingModel) throws Exception;

	/** 
	* @Title: getOneByAuthMapping 
	* @Description: 根据authMappingModel得到AuthMappingModel集合，取出第一个，
	* 一般已知authMappingModel条件唯一时，用此方法
	* @param authMappingModel
	* @return    
	* @return AuthMappingModel  
	* @throws 
	*/
	public AuthMappingModel getOneByAuthMapping(AuthMappingModel authMappingModel) throws Exception;

	/** 
	* @Title: updateInfo 
	* @Description: 更新AuthMappingModel
	* @param authMappingModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer updateInfo(AuthMappingModel authMappingModel) throws Exception;

	/** 
	* @Title: deleteInfo 
	* @Description: 根据主键mappingId删除AuthMappingModel
	* @param mappingId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfo(Integer mappingId) throws Exception;

	/** 
	* @Title: getOneByMappingId 
	* @Description: 根据主键mappingId得到AuthMappingModel
	* @param mappingId
	* @return    
	* @return AuthMappingModel  
	* @throws 
	*/
	AuthMappingModel getOneByMappingId(Integer mappingId) throws Exception;

	/** 
	* @Title: getAuthMappListByReqUrlAndAppId 
	* @Description:根据appId,requestUrl得到AuthMappingModel
	* @param appId
	* @param requestUrl
	* @return    
	* @return List<AuthMappingModel>  
	* @throws 
	*/
	public List<AuthMappingModel> getAuthMappListByReqUrlAndAppId(String appId, String requestUrl) throws Exception;

	/** 
	* @Title: getOneByProjectIdAndServerId 
	* @Description: 根据projectId,serverId得到AuthMappingModel
	* @param projectId
	* @param serverId
	* @return    
	* @return AuthMappingModel  
	* @throws 
	*/
	public AuthMappingModel getOneByProjectIdAndServerId(Integer projectId, Integer serverId) throws Exception;

	/** 
	* @Title: getOneByRequestTypeAndRequestUrl 
	* @Description: 根据requestType,requestUrl得到AuthMappingModel
	* @param requestType
	* @param requestUrl
	* @return    
	* @return AuthMappingModel  
	* @throws 
	*/
	public AuthMappingModel getOneByRequestTypeAndRequestUrl(String requestType, String requestUrl) throws Exception;

	/**
	* @Title: selectListsByAppid 
	* @Description: 根据appId获取其下所有渠道中的默认方案对应的authMapp
	* @param appId
	* @return    
	* @return List<InterfaceInfoDto>
	* @throws
	 */
	public List<InterfaceInfoDto> selectListsByAppid(String appId) throws Exception;

	/** 
	* @Title: getMappAndServerByProId 
	* @Description: 根据方案编号获取对应的服务信息列表
	* @param projectId
	* @return    
	* @return List<AuthMappingDto>  
	* @throws 
	*/
	public List<AuthMappingDto> getMappAndServerByProId(Integer projectId) throws Exception;
	
	
	/** 
	* @Title: deleteByProjectId 
	* @Description: 根据方案编号删除对应的方案服务关系信息
	* @param projectId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteByProjectId(Integer projectId) throws Exception;

	
	/** 
	* @Title: judgeAuthMappInfo 
	* @Description: 验证数据是否符合服务关系表规则
	* @param model
	* @return    
	* @return String  
	* @throws 
	*/
	public String judgeAuthMappInfo(AuthMappingModel model) throws Exception;

	public List<Map<String,Object>> selectByProjectDataSource(Map<String,String> paramMap);

	public List<Map<String,Object>> selectByProjectDataSourceOriginal(Map<String,String> paramMap);

    List<AuthMappingModel> getMappingByAppId(String appid);
}
