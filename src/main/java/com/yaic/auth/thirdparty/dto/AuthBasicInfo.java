/************************************************************************
 * 描述 ：数据库表t_auth_encrypt对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;



/** 
* @ClassName: AuthBasicInfo 
* @Description: 配置页面添加渠道方案以及其鉴权信息对应bean
* @author ZZD
* @date 2018年6月30日 下午6:36:01 
*  
*/
public class AuthBasicInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7354882366921542863L;

	private Integer primaryId;
	
    /** 渠道代码 */
    private String dataSource;
    /** 渠道名称 */
    private String sourceName;
	
    /** 方案代码 */
    private String projectCode;
    /** 方案名称 */
    private String projectName;
	
//    private Integer authId;
    /** 鉴权类型 */
    private String authType;
    /**  */
    private String authParam1;
    /**  */
//    private String authParam2;
    /** 加密类型 */
    private String encryptType;
    /**  */
    private String encryptParam1;
    /** */
//    private String encryptParam2;
    
    private String addRole;
	
	public Integer getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(Integer primaryId) {
		this.primaryId = primaryId;
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
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAddRole() {
		return addRole;
	}
	public void setAddRole(String addRole) {
		this.addRole = addRole;
	}
    
}
