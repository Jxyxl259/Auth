/************************************************************************
 * 描述 ：数据库表t_auth_encrypt对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;

import com.yaic.auth.thirdparty.model.AuthEncryptModel;


/** 
* @ClassName: AuthEncryptDto 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:52:21 
*  
*/
public class AuthEncryptDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7354882366921542863L;

    private Integer authId;
    /** 鉴权类型 */
    private String authType;
    /**  */
    private String authParam1;
    /**  */
    private String authParam2;
    /** 加密类型 */
    private String encryptType;
    /**  */
    private String encryptParam1;
    /** */
    private String encryptParam2;
    
	public AuthEncryptDto() {}
	
	
	public AuthEncryptDto(AuthEncryptModel model) {
		if(model == null){ model = new AuthEncryptModel(); }
		this.authId = model.getAuthId();
		this.authType = model.getAuthType();
		this.authParam1 = model.getAuthParam1();
		this.authParam2 = model.getAuthParam2();
		this.encryptType = model.getEncryptType();
		this.encryptParam1 = model.getEncryptParam1();
		this.encryptParam2 = model.getEncryptParam2();
	}
	
	public AuthEncryptDto(AuthEncryptModel model,String noId) {
		if(model == null){ model = new AuthEncryptModel(); }
		this.authType = model.getAuthType();
		this.authParam1 = model.getAuthParam1();
		this.authParam2 = model.getAuthParam2();
		this.encryptType = model.getEncryptType();
		this.encryptParam1 = model.getEncryptParam1();
		this.encryptParam2 = model.getEncryptParam2();
	}
	
	public Integer getAuthId() {
		return authId;
	}
	public void setAuthId(Integer authId) {
		this.authId = authId;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthParam1() {
		return authParam1;
	}
	public void setAuthParam1(String authParam1) {
		this.authParam1 = authParam1;
	}
	public String getAuthParam2() {
		return authParam2;
	}
	public void setAuthParam2(String authParam2) {
		this.authParam2 = authParam2;
	}
	public String getEncryptType() {
		return encryptType;
	}
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	public String getEncryptParam1() {
		return encryptParam1;
	}
	public void setEncryptParam1(String encryptParam1) {
		this.encryptParam1 = encryptParam1;
	}
	public String getEncryptParam2() {
		return encryptParam2;
	}
	public void setEncryptParam2(String encryptParam2) {
		this.encryptParam2 = encryptParam2;
	}
    
    
}
