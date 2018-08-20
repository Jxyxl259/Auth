package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;
import java.util.Date;

/** 回调页面显示dto */
public class CallbackUrlViewDto implements Serializable {

	private static final long serialVersionUID = 3228413504511730586L;

	/** 回调url id */
	private Integer callbackUrlId;
	/** 方案id */
	private Integer projectId;
	/** 方案名称 */
	private String projectName;
	/** 环境类型 */
	private String callbackEnv;
	/** 回调类型 */
	private String callbackType;
	/** 回调url */
	private String callbackUrl;
	/** 回调方法 */
	private String callbackMethod;
	/** 有效标识,1有效,0无效 */
	private Integer validFlag;
	/** 创建时间,默认插入时间 */
	private Date createdDate;
	/** 创建用户,插入用户 */
	private String createdUser;
	/** 更新时间,默认插入更新时间 */
	private Date updatedDate;
	/** 更新用户,插入用户 */
	private String updatedUser;

	public Integer getCallbackUrlId() {
		return callbackUrlId;
	}

	public void setCallbackUrlId(Integer callbackUrlId) {
		this.callbackUrlId = callbackUrlId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCallbackEnv() {
		return callbackEnv;
	}

	public void setCallbackEnv(String callbackEnv) {
		this.callbackEnv = callbackEnv;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getCallbackMethod() {
		return callbackMethod;
	}

	public void setCallbackMethod(String callbackMethod) {
		this.callbackMethod = callbackMethod;
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
		return "CallbackUrlViewDto [callbackUrlId=" + callbackUrlId + ", projectId=" + projectId + ", projectName="
				+ projectName + ", callbackType=" + callbackType + ", callbackUrl=" + callbackUrl + ", callbackMethod="
				+ callbackMethod + ", validFlag=" + validFlag + ", createdDate=" + createdDate + ", createdUser="
				+ createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callbackMethod == null) ? 0 : callbackMethod.hashCode());
		result = prime * result + ((callbackType == null) ? 0 : callbackType.hashCode());
		result = prime * result + ((callbackUrl == null) ? 0 : callbackUrl.hashCode());
		result = prime * result + ((callbackUrlId == null) ? 0 : callbackUrlId.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
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
		CallbackUrlViewDto other = (CallbackUrlViewDto) obj;
		if (callbackMethod == null) {
			if (other.callbackMethod != null)
				return false;
		} else if (!callbackMethod.equals(other.callbackMethod))
			return false;
		if (callbackType == null) {
			if (other.callbackType != null)
				return false;
		} else if (!callbackType.equals(other.callbackType))
			return false;
		if (callbackUrl == null) {
			if (other.callbackUrl != null)
				return false;
		} else if (!callbackUrl.equals(other.callbackUrl))
			return false;
		if (callbackUrlId == null) {
			if (other.callbackUrlId != null)
				return false;
		} else if (!callbackUrlId.equals(other.callbackUrlId))
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
