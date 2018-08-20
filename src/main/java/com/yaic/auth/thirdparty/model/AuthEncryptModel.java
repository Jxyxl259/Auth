package com.yaic.auth.thirdparty.model;

import java.io.Serializable;
import java.util.Date;

import com.yaic.auth.common.BaseDto;


/** 
* @ClassName: AuthEncryptModel 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:53:47 
*  
*/
public class AuthEncryptModel extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1660213532311470988L;
    /**  */
    private Integer authId;
    /** 鉴权类型 */
    private String authType;
    /**  */
    private String authParam1;
    /**  */
    private String authParam2;
    /** 加密类型 */
    private String encryptType;
    /** 存储加密的KEY */
    private String encryptParam1;
    /** */
    private String encryptParam2;
    
    private String appId;
    
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

    public AuthEncryptModel () {
        super ();
    }

    public Integer getAuthId () {
        return authId;
    }

    public void setAuthId ( Integer authId ) {
        this.authId = authId;
    }

    public String getAuthType () {
        return authType;
    }

    public void setAuthType ( String authType ) {
        this.authType = authType;
    }

    public String getAuthParam1 () {
        return authParam1;
    }

    public void setAuthParam1 ( String authParam1 ) {
        this.authParam1 = authParam1;
    }

    public String getAuthParam2 () {
        return authParam2;
    }

    public void setAuthParam2 ( String authParam2 ) {
        this.authParam2 = authParam2;
    }

    public String getEncryptType () {
        return encryptType;
    }

    public void setEncryptType ( String encryptType ) {
        this.encryptType = encryptType;
    }

    public String getEncryptParam1 () {
        return encryptParam1;
    }

    public void setEncryptParam1 ( String encryptParam1 ) {
        this.encryptParam1 = encryptParam1;
    }

    public String getEncryptParam2 () {
        return encryptParam2;
    }

    public void setEncryptParam2 ( String encryptParam2 ) {
        this.encryptParam2 = encryptParam2;
    }

    public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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
        return "AuthEncryptModel [authId=" + authId + ", authType=" + authType + ", authParam1=" + authParam1
                + ", authParam2=" + authParam2 + ", encryptType=" + encryptType + ", encryptParam1=" + encryptParam1
                + ", encryptParam2=" + encryptParam2 + ", validFlag=" + validFlag + ", createdDate=" + createdDate
                + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser
                + "]";
    }

}