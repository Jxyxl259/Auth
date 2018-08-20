/************************************************************************
 * 描述 ：数据库表t_server对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;

import com.yaic.auth.thirdparty.model.ServerModel;


/** 
* @ClassName: ServerDto 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:52:44 
*  
*/
public class ServerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
    
    
	public ServerDto() {}
	
	
	public ServerDto(ServerModel model) {
		
		if(model == null){ model = new ServerModel(); }
		this.serverId = model.getServerId();
		this.serverType = model.getServerType();
		this.serverVersion = model.getServerVersion();
		this.serverEnv = model.getServerEnv();
		this.serverStatus = model.getServerStatus();
		this.systemName = model.getSystemName();
		this.serverUrl = model.getServerUrl();
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
		this.serverType = serverType;
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
    
    

}
