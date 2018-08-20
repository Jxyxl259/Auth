/************************************************************************
 * 描述 ：数据库表t_account对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;

import com.yaic.auth.thirdparty.model.AccountModel;


/** 
* @ClassName: AccountDto 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:52:06 
*  
*/
public class AccountDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 146541698607044778L;
	
//	private Integer accountId;
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
	
//	/** 是否需要新增标志 YES是  NO否 */
//	private String addSign;
    
//	private List<DataSourceDto> dataSourList;
    
	
    public AccountDto() {}

	public AccountDto(AccountModel model) {
		
//		this.accountId = model.getAccountId();
		this.accountName = model.getAccountName();
		this.appCode = model.getAppCode();
		this.appId = model.getAppId();
		this.email = model.getEmail();
		this.mobile = model.getMobile();
	}

//	public Integer getAccountId() {
//		return accountId;
//	}
//
//	public void setAccountId(Integer accountId) {
//		this.accountId = accountId;
//	}

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

//	public String getAddSign() {
//		return addSign;
//	}
//
//	public void setAddSign(String addSign) {
//		this.addSign = addSign;
//	}
//	

//	public List<DataSourceDto> getDataSourList() {
//		return dataSourList;
//	}
//
//	public void setDataSourList(List<DataSourceDto> dataSourList) {
//		this.dataSourList = dataSourList;
//	}
	
}
