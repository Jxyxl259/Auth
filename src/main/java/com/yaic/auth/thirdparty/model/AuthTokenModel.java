package com.yaic.auth.thirdparty.model;

import java.io.Serializable;
import java.util.Date;

import com.yaic.auth.common.BaseDto;

/**
 * @ClassName: AuthTokenModel
 * @Description: TODO
 * @author zhaoZD
 * @date 2018年6月17日 下午9:54:32
 * 
 */
public class AuthTokenModel extends BaseDto implements Serializable {

	private static final long serialVersionUID = 4715439354826409675L;
	/** 主键自增 */
	private Integer tokenId;
	/**  */
	private String appId;
	/**  */
	private String appSecret;
	/**  */
	private String openId;
	/**  */
	private String accessToken;
	/** */
	private String refreshToken;
	/**  */
	private Date expiretime;
	/**  */
	private Date refExpireTime;
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

	public AuthTokenModel() {
		super();
	}

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
	}

	public Date getRefExpireTime() {
		return refExpireTime;
	}

	public void setRefExpireTime(Date refExpireTime) {
		this.refExpireTime = refExpireTime;
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
		return "AuthTokenModel [tokenId=" + tokenId + ", appId=" + appId + ", appSecret=" + appSecret + ", openId="
				+ openId + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", expiretime="
				+ expiretime + ", refExpireTime=" + refExpireTime + ", validFlag=" + validFlag + ", createdDate="
				+ createdDate + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser="
				+ updatedUser + "]";
	}
	

}