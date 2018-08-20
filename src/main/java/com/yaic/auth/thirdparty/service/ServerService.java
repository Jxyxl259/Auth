package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.model.ServerModel;

/** 
* @ClassName: ServerService 
* @Description: 服务接口
* @author 
* @date 2018年6月17日 下午10:03:54 
*  
*/
public interface ServerService {
	
	/** 
	* @Title: getList 
	* @Description: 根据serverModel得到ServerModel集合
	* @param serverModel
	* @return    
	* @return List<ServerModel>  
	* @throws 
	*/
	public List<ServerModel> getList(ServerModel serverModel) throws Exception;

	/** 
	* @Title: getOneByServer 
	* @Description: 根据serverModel得到ServerModel集合，取出第一个，
	* 一般已知serverModel条件唯一时，用此方法
	* @param serverModel
	* @return    
	* @return ServerModel  
	* @throws 
	*/
	public ServerModel getOneByServer(ServerModel serverModel) throws Exception;

	/** 
	* @Title: addInfo 
	* @Description: 新增记录
	* @param serverModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer addInfo(ServerModel serverModel) throws Exception;

	/** 
	* @Title: updateInfo 
	* @Description: 修改记录信息
	* @param serverModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer updateInfo(ServerModel serverModel) throws Exception;

	/** 
	* @Title: deleteInfo 
	* @Description: 根据主键编号删除数据
	* @param serverId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfo(Integer serverId) throws Exception;

	/** 
	* @Title: getOneByServerId 
	* @Description: 根据projectId精确查询对象
	* @param serverId
	* @return    
	* @return ServerModel  
	* @throws 
	*/
	public ServerModel getOneByServerId(Integer serverId) throws Exception;

	/** 
	* @Title: getOneByServerUrl 
	* @Description: 根据serverUrl得到ServerModel
	* @param serverUrl
	* @return    
	* @return ServerModel  
	* @throws 
	*/
	public ServerModel getOneByServerUrl(String serverUrl) throws Exception;

	/** 
	* @Title: deleteInfoByIds 
	* @Description: 批量删除
	* @param ids
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfoByIds(List<String> ids) throws Exception;
	
}
