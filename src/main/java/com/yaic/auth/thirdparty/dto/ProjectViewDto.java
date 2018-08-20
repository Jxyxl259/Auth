package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;
import java.util.Date;

import com.yaic.auth.common.BaseDto;

/** 为方案页面显示 */
public class ProjectViewDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = -7675009170321436351L;

	/** 主键 */
	private Integer projectId;
	/** 方案代码 */
	private String projectCode;
	/** 方案名称 */
	private String projectName;
	/** app_id */
	private Integer accountId;
	/** 鉴权id,t_auth表主键 */
	private Integer authId;
	/** 渠道名称 */
	private String sourceName;
	/** 有效标识,1有效,0无效 */
	private Integer validFlag;
	/** 是否默认方案,1,是:0,否 */
	private String isDefault;
	/** 创建时间,默认插入时间 */
	private Date createdDate;
	/** 创建用户,插入用户 */
	private String createdUser;
	/** 更新时间,默认插入更新时间 */
	private Date updatedDate;
	/** 更新用户 */
	private String updatedUser;

	public ProjectViewDto() {
		super();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
		return "ProjectViewDto [projectId=" + projectId + ", projectCode=" + projectCode + ", projectName="
				+ projectName + ", accountId=" + accountId + ", authId=" + authId + ", sourceName=" + sourceName
				+ ", validFlag=" + validFlag + ", isDefault=" + isDefault + ", createdDate=" + createdDate
				+ ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((authId == null) ? 0 : authId.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((projectCode == null) ? 0 : projectCode.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
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
		ProjectViewDto other = (ProjectViewDto) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
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
		if (isDefault == null) {
			if (other.isDefault != null)
				return false;
		} else if (!isDefault.equals(other.isDefault))
			return false;
		if (projectCode == null) {
			if (other.projectCode != null)
				return false;
		} else if (!projectCode.equals(other.projectCode))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
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
