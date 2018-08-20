package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.dto.CallbackUrlViewDto;
import com.yaic.auth.thirdparty.model.CallbackUrlModel;

/** 
* @ClassName: CallbackUrlService 
* @Description: 回调接口
* @author 
* @date 2018年6月17日 下午9:59:26 
*  
*/
public interface CallbackUrlService {

	/** 
	* @Title: addInfo 
	* @Description: 添加CallbackUrlModel
	* @param callbackUrlModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer addInfo(CallbackUrlModel callbackUrlModel) throws Exception;

	/** 
	* @Title: getList 
	* @Description: 根据callbackUrlModel得到CallbackUrlModel集合
	* @param callbackUrlModel
	* @return    
	* @return List<CallbackUrlModel>  
	* @throws 
	*/
	public List<CallbackUrlModel> getList(CallbackUrlModel callbackUrlModel) throws Exception;

	/** 
	* @Title: updateInfo 
	* @Description: 更新CallbackUrlModel
	* @param callbackUrlModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer updateInfo(CallbackUrlModel callbackUrlModel) throws Exception;

	/** 
	* @Title: deleteInfo 
	* @Description: 根据主键callbackUrlId删除CallbackUrlModel
	* @param callbackUrlId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfo(Integer callbackUrlId) throws Exception;

	/** 
	* @Title: getOneByCallbackUrlId 
	* @Description: 根据主键callbackUrlId得到CallbackUrlModel
	* @param callbackUrlId
	* @return    
	* @return CallbackUrlModel  
	* @throws 
	*/
	public CallbackUrlModel getOneByCallbackUrlId(Integer callbackUrlId) throws Exception;

	/** 
	* @Title: getOneByCallbackUrl 
	* @Description: 根据callbackUrlModel得到CallbackUrlModel集合，取出第一个，
	* 一般已知callbackUrlModel条件唯一时，用此方法
	* @param callbackUrlModel
	* @return    
	* @return CallbackUrlModel  
	* @throws 
	*/
	public CallbackUrlModel getOneByCallbackUrl(CallbackUrlModel callbackUrlModel) throws Exception;

	/** 
	* @Title: deleteByProjectId 
	* @Description: 根据方案编号删除对应回调信息
	* @param projectId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteByProjectId(Integer projectId) throws Exception;
	
	/** 
	* @Title: getCallbackUrlViewDto
	* @Description: 根据前台传过来callbackUrlModel获取CallbackUrlViewDto，主要负责前台展示
	* @param callbackUrlModel
	* @return    
	* @return List<CallbackUrlViewDto>
	* @throws 
	*/
	public List<CallbackUrlViewDto> getCallbackUrlViewDto(CallbackUrlModel callbackUrlModel) throws Exception;

	/** 
	* @Title: getOneByProjectIdAndCallbackMethod 
	* @Description: 根据projectId，callbackMethod得到CallbackUrlModel
	* @param projectId
	* @param callbackMethod
	* @return    
	* @return CallbackUrlModel  
	* @throws 
	*/
	public CallbackUrlModel getOneByProjectIdAndCallbackMethod(Integer projectId, String callbackMethod);
	
	/** 
	* @Title: deleteInfoByIds 
	* @Description: 根据callbackUrlIds集合，量删除CallbackUrlModel
	* @param callbackUrlIds
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfoByIds(List<String> callbackUrlIds)throws Exception;

}
