package com.yaic.auth.thirdparty.dto;

import java.util.Date;

/**
 * @Description:
 * @author: jiangxy
 * @date: 2018\7\25 0025 18:17
 */
public class AuthCallbackInfoDto {

	/** id */
	private java.lang.Integer id;

	/** app_id */
	private java.lang.String appId;

	/** 协议号 */
	private java.lang.String projectCode;

	/** 渠道代号 */
	private java.lang.String dataSource;

	/** 回调内容 */
	private java.lang.String content;

	/** 业务单号 */
	private java.lang.String businessNo;

	/** 处理类型 01:支付成功回调 11:退保回调*/
	private java.lang.String dealType;

	/** 处理状态:0未处理成功,1已处理成功 */
	private java.lang.Integer dealStatus;

	/** 处理次数 */
	private java.lang.Integer dealCount;

	/** 创建人 */
	private java.lang.String createdUser;

	/** 创建时间 */
	private java.util.Date createdDate;

	/** 更新人 */
	private java.lang.String updatedUser;

	/** 更新时间 */
	private java.util.Date updatedDate;

	/** 是否有效:0正常,1作废值 */
	private java.lang.Integer invalidFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Integer getDealCount() {
		return dealCount;
	}

	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getInvalidFlag() {
		return invalidFlag;
	}

	public void setInvalidFlag(Integer invalidFlag) {
		this.invalidFlag = invalidFlag;
	}
}