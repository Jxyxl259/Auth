package com.yaic.auth.thirdparty.model;

import java.io.Serializable;
import java.util.Date;

import com.yaic.auth.common.BaseDto;


/** 
* @ClassName: ServerModel 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:54:55 
*  
*/
public class ServerModel extends BaseDto implements Serializable {

    private static final long serialVersionUID = 3524234621711226829L;

    /** */
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
    /** 有效标识,1有效,0无效 */
    private Integer validFlag;
    /** 创建时间,默认插入时间 */
    private Date createdDate;
    /** 创建用户,插入用户 */
    private String createdUser;
    /** 更新时间,默认插入更新时间 */
    private Date updatedDate;
    /** 更新用户 */
    private String updatedUser;
    
    public ServerModel() {
		super();
		// TODO Auto-generated constructor stub
	}

    public Integer getServerId () {
        return serverId;
    }

    public void setServerId ( Integer serverId ) {
        this.serverId = serverId;
    }

    public String getServerType () {
        return serverType;
    }

    public void setServerType ( String serverType ) {
        this.serverType = serverType;
    }

    public String getServerVersion () {
        return serverVersion;
    }

    public void setServerVersion ( String serverVersion ) {
        this.serverVersion = serverVersion;
    }

    public String getServerEnv () {
        return serverEnv;
    }

    public void setServerEnv ( String serverEnv ) {
        this.serverEnv = serverEnv;
    }

    public String getServerStatus () {
        return serverStatus;
    }

    public void setServerStatus ( String serverStatus ) {
        this.serverStatus = serverStatus;
    }

    public String getSystemName () {
        return systemName;
    }

    public void setSystemName ( String systemName ) {
        this.systemName = systemName;
    }

    public String getServerUrl () {
        return serverUrl;
    }

    public void setServerUrl ( String serverUrl ) {
        this.serverUrl = serverUrl;
    }

    public Integer getValidFlag () {
        return validFlag;
    }

    public void setValidFlag ( Integer validFlag ) {
        this.validFlag = validFlag;
    }

    public Date getCreatedDate () {
        return createdDate;
    }

    public void setCreatedDate ( Date createdDate ) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser () {
        return createdUser;
    }

    public void setCreatedUser ( String createdUser ) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedDate () {
        return updatedDate;
    }

    public void setUpdatedDate ( Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedUser () {
        return updatedUser;
    }

    public void setUpdatedUser ( String updatedUser ) {
        this.updatedUser = updatedUser;
    }

    @Override
    public String toString () {
        return "ServerModel [serverId=" + serverId + ", serverType=" + serverType + ", serverVersion=" + serverVersion
                + ", serverEnv=" + serverEnv + ", serverStatus=" + serverStatus + ", systemName=" + systemName
                + ", serverUrl=" + serverUrl + ", validFlag=" + validFlag + ", createdDate=" + createdDate
                + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser
                + "]";
    }

	
}