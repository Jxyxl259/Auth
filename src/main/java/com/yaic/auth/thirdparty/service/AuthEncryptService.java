package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.model.AuthEncryptModel;


/** 
* @ClassName: AuthEncryptService 
* @Description: 权限、加密接口
* @author 
* @date 2018年6月17日 下午9:57:47 
*  
*/
public interface AuthEncryptService {
	
	/** 
	* @Title: addInfo 
	* @Description:添加AuthEncryptModel
	* @param authEncryptModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer addInfo(AuthEncryptModel authEncryptModel) throws Exception;

	/** 
	* @Title: getList 
	* @Description:  根据authEncryptModel查询得到AuthEncryptModel
	* @param authEncryptModel
	* @return    
	* @return List<AuthEncryptModel>  
	* @throws 
	*/
	public List<AuthEncryptModel> getList(AuthEncryptModel authEncryptModel)throws Exception;

	/** 
	* @Title: updateInfo 
	* @Description: 根据authEncryptModel更新AuthEncryptModel
	* @param authEncryptModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer updateInfo(AuthEncryptModel authEncryptModel) throws Exception;

	/** 
	* @Title: deleteInfo 
	* @Description: 根据主键authId删除AuthEncryptModel
	* @param authId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfo(Integer authId) throws Exception;

	/** 
	* @Title: getOneByAuthId 
	* @Description: 根据主键authId得到AuthEncryptModel
	* @param authId
	* @return    
	* @return AuthEncryptModel  
	* @throws 
	*/
	public AuthEncryptModel getOneByAuthId(Integer authId) throws Exception;

	/** 
	* @Title: selectByAppid 
	* @Description: 根据账户编号获取对应渠道下所有方案对应的鉴权信息集合
	* @param appid
	* @return    
	* @return List<AuthEncryptModel>  
	* @throws 
	*/
	List<AuthEncryptModel> selectByAppid(String appid) throws Exception;
}
