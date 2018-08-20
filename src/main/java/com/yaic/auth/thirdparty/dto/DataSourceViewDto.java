package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;
import java.util.Date;

public class DataSourceViewDto implements Serializable {
	private static final long serialVersionUID = -1716207042351849061L;
	private Integer dataSourceId;
	/** 渠道代码 */
	private String dataSource;
	/** 渠道名称 */
	private String sourceName;
	/** app_id */
	private String appId;
	/** 用户名称 */
	private String accountName;
	/** 鉴权id,t_auth表主键 */
	private Integer authId;
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

	public Integer getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
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
		return "DataSourceViewDto [dataSourceId=" + dataSourceId + ", dataSource=" + dataSource + ", sourceName="
				+ sourceName + ", appId=" + appId + ", accountName=" + accountName + ", authId=" + authId
				+ ", validFlag=" + validFlag + ", createdDate=" + createdDate + ", createdUser=" + createdUser
				+ ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + ((authId == null) ? 0 : authId.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((dataSource == null) ? 0 : dataSource.hashCode());
		result = prime * result + ((dataSourceId == null) ? 0 : dataSourceId.hashCode());
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result + ((updatedUser == null) ? 0 : updatedUser.hashCode());
		result = prime * result + ((validFlag == null) ? 0 : validFlag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataSourceViewDto other = (DataSourceViewDto) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (authId == null) {
			if (other.authId != null)
				return false;
		} else if (!authId.equals(other.authId))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdUser == null) {
			if (other.createdUser != null)
				return false;
		} else if (!createdUser.equals(other.createdUser))
			return false;
		if (dataSource == null) {
			if (other.dataSource != null)
				return false;
		} else if (!dataSource.equals(other.dataSource))
			return false;
		if (dataSourceId == null) {
			if (other.dataSourceId != null)
				return false;
		} else if (!dataSourceId.equals(other.dataSourceId))
			return false;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		if (updatedUser == null) {
			if (other.updatedUser != null)
				return false;
		} else if (!updatedUser.equals(other.updatedUser))
			return false;
		if (validFlag == null) {
			if (other.validFlag != null)
				return false;
		} else if (!validFlag.equals(other.validFlag))
			return false;
		return true;
	}

}
