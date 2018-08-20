/************************************************************************
 * 描述 ：数据库表t_auth_mapping对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.yaic.auth.thirdparty.model.AuthMappingModel;


/** 
* @ClassName: AuthMappingDto 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:52:25 
*  
*/
public class AuthMappingDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8610104618663188074L;
	
	private Integer mappingId;
//    /** 方案id */
//    private Integer projectId;
    /** 请求类型 */
    private String requestType;
    /** 请求url */
    private String requestUrl;
    /** 服务id */
    private Integer serverId;
    
    /** 服务类型 */
    private String serverType;
    /** 服务版本号 */
    private String serverVersion;
    /** 环境类型 */
    private String serverEnv;
    /** 服务状态 */
    private String serverStatus;
    /**  */
    private String systemName;
    /** 服务url */
    private String serverUrl;
    
    //服务小类,由服务类型中拆出
    private String serverSmallType;
    
	public AuthMappingDto() {}
	
	public AuthMappingDto(AuthMappingModel model) {
		if(model == null){ model = new AuthMappingModel(); }
		this.mappingId = model.getMappingId();
		this.requestType = model.getRequestType();
		this.requestUrl = model.getRequestUrl();
		this.serverId = model.getServerId();
	}
//	
//	public AuthMappingDto(AuthMappingModel model,String noId) {
//		if(model == null){ model = new AuthMappingModel(); }
//		this.mappingId = model.getMappingId();
//		this.requestType = model.getRequestType();
//		this.requestUrl = model.getRequestUrl();
//		this.serverId = model.getServerId();
//	}
	
	public Integer getMappingId() {
		return mappingId;
	}
	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}
//	public Integer getProjectId() {
//		return projectId;
//	}
//	public void setProjectId(Integer projectId) {
//		this.projectId = projectId;
//	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public Integer getServerId() {
		return serverId;
	}
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
    
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		
		if(StringUtils.isNotBlank(serverType) && serverType.indexOf("-") > 0){
			String[] type = serverType.split("-");
			this.serverType = type[0];
			this.serverSmallType = serverType;
		}else{
			this.serverType = serverType;
		}
	}
	public String getServerVersion() {
		return serverVersion;
	}
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}
	public String getServerEnv() {
		return serverEnv;
	}
	public void setServerEnv(String serverEnv) {
		this.serverEnv = serverEnv;
	}
	public String getServerStatus() {
		return serverStatus;
	}
	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getServerSmallType() {
		return serverSmallType;
	}
	public void setServerSmallType(String serverSmallType) {
		this.serverSmallType = serverSmallType;
	}

}
