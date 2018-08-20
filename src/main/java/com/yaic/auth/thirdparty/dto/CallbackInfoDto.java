package com.yaic.auth.thirdparty.dto;

import java.util.Date;

/**
 * @Description: outerpay 支付成功回调消息体
 * @author: jiangxy
 * @date: 2018\7\25 0025 14:17
 */
public class CallbackInfoDto {

	/** id */
	private java.lang.Integer id;

	/** app_id */
	private java.lang.String appId;

	/** 协议号 对应 方案代码projectCode */
	private java.lang.String agrtCode;

	/** 渠道代号 */
	private java.lang.String dataSource;

	/** 回调内容 */
	private java.lang.String content;

	/** 业务单号 */
	private java.lang.String businessNo;

	/** 处理类型 01:支付成功回调 11:退保回调 */
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

	/**
	 * 获取属性app_id的值
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * 设置属性app_id的值
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 获取协议编号
	 */
	public String getAgrtCode() {
		return agrtCode;
	}

	/**
	 * 设置协议编号
	 */
	public void setAgrtCode(String agrtCode) {
		this.agrtCode = agrtCode;
	}

	/**
	 * 获取渠道代号
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * 设置渠道代号
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取属性回调内容的值
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置属性回调内容的值
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取业务单号的值
	 */
	public String getBusinessNo() {
		return businessNo;
	}

	/**
	 * 设置业务单号的值
	 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	/**
	 * 获取处理类型的值
	 */
	public String getDealType() {
		return dealType;
	}

	/**
	 * 设置处理类型的值
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	/**
	 * 获取属性处理状态:0未处理成功,1已处理成功
	 */
	public Integer getDealStatus() {
		return dealStatus;
	}

	/**
	 * 设置属性处理状态:0未处理成功,1已处理成功的值,2处理中
	 */
	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	/**
	 * 获取处理状态:0未处理成功,1已处理成功的值,2处理中
	 */
	public Integer getDealCount() {
		return dealCount;
	}

	/**
	 * 设置属性处理次数的值
	 */
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

	/**
	 * 获取属性是否有效:0正常,1作废值的值
	 */
	public Integer getInvalidFlag() {
		return invalidFlag;
	}

	/**
	 * 设置属性是否有效:0正常,1作废值的值
	 */
	public void setInvalidFlag(Integer invalidFlag) {
		this.invalidFlag = invalidFlag;
	}
}