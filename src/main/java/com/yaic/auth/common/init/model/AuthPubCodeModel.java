package com.yaic.auth.common.init.model;

import java.io.Serializable;
import java.util.Date;

/** t_auth_pub_code*/
public class AuthPubCodeModel implements Serializable {

	private static final long serialVersionUID = 2511676861287202313L;

	private Integer parameterId;
	private String parameterName;
	private String parameterValue;
	private String parameterDescribe;
	private String parameterType;
	private String typeDescribe;
	private Integer parentId;
	private Integer level;
	private Integer validFlag;
	/** 创建时间,默认插入时间 */
	private Date createdDate;
	/** 创建用户,插入用户 */
	private String createdUser;
	/** 更新时间,默认插入更新时间 */
	private Date updatedDate;
	/** 更新用户 */
	private String updatedUser;
	public Integer getParameterId() {
		return parameterId;
	}
	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public String getParameterDescribe() {
		return parameterDescribe;
	}
	public void setParameterDescribe(String parameterDescribe) {
		this.parameterDescribe = parameterDescribe;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getTypeDescribe() {
		return typeDescribe;
	}
	public void setTypeDescribe(String typeDescribe) {
		this.typeDescribe = typeDescribe;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
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
		return "AuthPubCodeModel [parameterId=" + parameterId + ", parameterName=" + parameterName + ", parameterValue="
				+ parameterValue + ", parameterDescribe=" + parameterDescribe + ", parameterType=" + parameterType
				+ ", typeDescribe=" + typeDescribe + ", parentId=" + parentId + ", level=" + level + ", validFlag="
				+ validFlag + ", createdDate=" + createdDate + ", createdUser=" + createdUser + ", updatedDate="
				+ updatedDate + ", updatedUser=" + updatedUser + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((parameterDescribe == null) ? 0 : parameterDescribe.hashCode());
		result = prime * result + ((parameterId == null) ? 0 : parameterId.hashCode());
		result = prime * result + ((parameterName == null) ? 0 : parameterName.hashCode());
		result = prime * result + ((parameterType == null) ? 0 : parameterType.hashCode());
		result = prime * result + ((parameterValue == null) ? 0 : parameterValue.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((typeDescribe == null) ? 0 : typeDescribe.hashCode());
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
		AuthPubCodeModel other = (AuthPubCodeModel) obj;
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
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (parameterDescribe == null) {
			if (other.parameterDescribe != null)
				return false;
		} else if (!parameterDescribe.equals(other.parameterDescribe))
			return false;
		if (parameterId == null) {
			if (other.parameterId != null)
				return false;
		} else if (!parameterId.equals(other.parameterId))
			return false;
		if (parameterName == null) {
			if (other.parameterName != null)
				return false;
		} else if (!parameterName.equals(other.parameterName))
			return false;
		if (parameterType == null) {
			if (other.parameterType != null)
				return false;
		} else if (!parameterType.equals(other.parameterType))
			return false;
		if (parameterValue == null) {
			if (other.parameterValue != null)
				return false;
		} else if (!parameterValue.equals(other.parameterValue))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (typeDescribe == null) {
			if (other.typeDescribe != null)
				return false;
		} else if (!typeDescribe.equals(other.typeDescribe))
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
