package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.model.AccountModel;


/**
 * 
 * @Title AccountService
 * @Description 账户对应接口
 * @author wangjs
 * @date 2018/06/19
 *
 */
public interface AccountService {


	/** 
	* @Title: getList 
	* @Description: 根据对象查询所有信息集合
	* @param accountModel
	* @return    
	* @return List<AccountModel>  
	* @throws 
	*/
	public List<AccountModel> getList(AccountModel accountModel) throws Exception;
	

	/** 
	* @Title: getOneByAccount 
	* @Description: 获取查询结果第一条数据
	* @param accountModel
	* @return    
	* @return AccountModel  
	* @throws 
	*/
	public AccountModel getOneByAccount(AccountModel accountModel) throws Exception;


	/** 
	* @Title: getOneByAppid 
	* @Description: 根据appId精确查询对象
	* @param appId
	* @return    
	* @return AccountModel  
	* @throws 
	*/
	public AccountModel getOneByAppid(String appId) throws Exception;

	/** 
	* @Title: getOneByAppCode 
	* @Description: 根据appCode精确查询对象
	* @param appCode
	* @return    
	* @return AccountModel  
	* @throws 
	*/
	public AccountModel getOneByAppCode(String appCode) throws Exception;

	/** 
	* @Title: getOneByAccountId 
	* @Description: 根据accountId精确查询对象
	* @param accountId
	* @return    
	* @return AccountModel  
	* @throws 
	*/
	public AccountModel getOneByAccountId(Integer accountId) throws Exception;

	/** 
	* @Title: addInfo 
	* @Description: 新增记录
	* @param accountModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer addInfo(AccountModel accountModel) throws Exception;

	/** 
	* @Title: updateInfo 
	* @Description: 修改记录信息
	* @param accountModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer updateInfo(AccountModel accountModel) throws Exception;

	
	/** 
	* @Title: deleteInfo 
	* @Description: 根据主键编号删除数据
	* @param accountId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfo(Integer accountId) throws Exception;

	
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
