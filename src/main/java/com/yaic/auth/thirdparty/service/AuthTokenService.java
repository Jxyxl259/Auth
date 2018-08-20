package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.model.AuthTokenModel;

/**
 * @ClassName: AuthTokenService
 * @Description: token接口
 * @author zhaoZD
 * @date 2018年6月17日 下午9:58:57
 * 
 */
public interface AuthTokenService {

	/**
	 * @Title: addInfo 
	 * @Description: 添加AuthTokenModel
	 * @param authTokenModel 
	 * @return 
	 * @return Integer 
	 * @throws
	 */
	public Integer addInfo(AuthTokenModel authTokenModel) throws Exception;

	/**
	 * @Title: getList 
	 * @Description: 根据authTokenModel得到AuthTokenModel集合
	 * @param authTokenModel 
	 * @return 
	 * @return List<AuthTokenModel> 
	 * @throws
	 */
	public List<AuthTokenModel> getList(AuthTokenModel authTokenModel) throws Exception;

	/**
	 * @Title: getOneByAuthToken 
	 * @Description: 根据authTokenModel得到AuthTokenModel集合，取出第一个，
	 * 一般已知authTokenModel条件唯一时，用此方法
	 * @param authTokenModel 
	 * @return 
	 * @return AuthTokenModel 
	 * @throws
	 */
	public AuthTokenModel getOneByAuthToken(AuthTokenModel authTokenModel) throws Exception;

	/**
	 * @Title: updateInfo 
	 * @Description: 更新AuthTokenModel
	 * @param authTokenModel
	 * @return 
	 * @return Integer 
	 * @throws
	 */
	public Integer updateInfo(AuthTokenModel authTokenModel) throws Exception;

	/**
	 * @Title: deleteInfo 
	 * @Description: 根据主键tokenId删除AuthTokenModel
	 * @param appId 
	 * @return 
	 * @return Integer 
	 * @throws
	 */
	public Integer deleteInfo(Integer tokenId) throws Exception;

	/**
	 * @Title: getOneByAppIdAndAppSecret 
	 * @Description: 根据appId，appSecret得到AuthTokenModel
	 * @param appId 
	 * @param appSecret
	 * @return 
	 * @return AuthTokenModel 
	 * @throws
	 */
	public AuthTokenModel getOneByAppIdAndAppSecret(String appId, String appSecret) throws Exception;

	/**
	 * @Title: deleteInfoByIds 
	 * @Description: 根据tokenId集合，批量删除AuthTokenModel
	 * @param appIds 
	 * @return 
	 * @return Integer 
	 * @throws
	 */
	public Integer deleteInfoByIds(List<String> tokenIds) throws Exception;
	
	/**
	 * @Title: getOneByTokenId 
	 * @Description: 通过主键tokenId查询对象
	 * @param tokenId
	 * @return 
	 * @return Integer 
	 * @throws
	 */
	public AuthTokenModel getOneByTokenId(Integer tokenId) throws Exception;

}
