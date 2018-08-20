package com.yaic.auth.service;

import java.util.Map;

import com.yaic.common.ResponseMessage;

public interface OAuthService {

	ResponseMessage getToken(String app_id, String app_secret, String data_source, String project_code) throws Exception;

	ResponseMessage refreshToken(String app_id, String app_secret, String refresh_token)  throws Exception;

	ResponseMessage token_auth(String access_token, String openid)  throws Exception;
	
	ResponseMessage interface_list(String access_token, String openid)  throws Exception;
	
	
	/**
	 * @param parameterType  
	* @Title: selectPubCodeByConditions 
	* @Description: TODO
	* @return
	* @throws Exception    
	* @return Map<String,String>  
	* @throws 
	*/
	Map<String,String> selectPubCodeByConditions(String parameterType) throws Exception;
}
