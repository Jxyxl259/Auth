package com.yaic.auth.thirdparty.model;

import java.io.Serializable;
import java.util.Date;

import com.yaic.auth.common.BaseDto;

/** 
* @ClassName: AccountModel 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:53:37 
*  
*/
public class AccountModel extends BaseDto implements Serializable {

	private static final long serialVersionUID = -7967795605389240699L;

	private Integer accountId;
	/** 用户名称 */
	private String accountName;
	/**  */
	private String appId;
	/** 手机号 */
	private String mobile;
	/** 邮箱地址 */
	private String email;
	/** */
	private String appCode;
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

	public AccountModel() {
		super();
	}

	public AccountModel(String appId) {
		this.appId = appId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	@Override
	public String toString() {
		return "AccountModel [accountId=" + accountId + ", accountName=" + accountName + ", appId=" + appId + ", mobile="
				+ mobile + ", email=" + email + ", appCode=" + appCode + ", validFlag=" + validFlag + ", createdDate="
				+ createdDate + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser="
				+ updatedUser + "]";
	}

}