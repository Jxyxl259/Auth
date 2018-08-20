package com.yaic.auth.thirdparty.model;

import java.io.Serializable;
import java.util.Date;

import com.yaic.auth.common.BaseDto;


/** 
* @ClassName: ProjectModel 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:54:50 
*  
*/
public class ProjectModel extends BaseDto implements Serializable {

    private static final long serialVersionUID = -8378589654951248315L;

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
    /** 渠道id */
    private Integer dataSourceId;
    /** 有效标识,1有效,0无效 */
    private Integer validFlag;
    /** 是否默认方案,1是默认方案0不是 */
    private Integer isDefault;
    /** 创建时间,默认插入时间 */
    private Date createdDate;
    /** 创建用户,插入用户 */
    private String createdUser;
    /** 更新时间,默认插入更新时间 */
    private Date updatedDate;
    /** 更新用户 */
    private String updatedUser;

    public ProjectModel () {
        super ();
    }

	public ProjectModel(Integer dataSourceId) {
		super();
		this.dataSourceId = dataSourceId;
	}
	
	public ProjectModel(int projectId, String projectCode, String projectName, int accountId, int authId,
			int dataSourceId, Integer validFlag, Date createdDate, String createdUser, Date updatedDate,
			String updatedUser) {
		super();
		this.projectId = projectId;
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.accountId = accountId;
		this.authId = authId;
		this.dataSourceId = dataSourceId;
		this.validFlag = validFlag;
		this.createdDate = createdDate;
		this.createdUser = createdUser;
		this.updatedDate = updatedDate;
		this.updatedUser = updatedUser;
	}

	public Integer getProjectId () {
        return projectId;
    }

    public void setProjectId ( Integer projectId ) {
        this.projectId = projectId;
    }

    public String getProjectCode () {
        return projectCode;
    }

    public void setProjectCode ( String projectCode ) {
        this.projectCode = projectCode;
    }

    public String getProjectName () {
        return projectName;
    }

    public void setProjectName ( String projectName ) {
        this.projectName = projectName;
    }

    public Integer getAccountId () {
        return accountId;
    }

    public void setAccountId ( Integer accountId ) {
        this.accountId = accountId;
    }

    public Integer getAuthId () {
        return authId;
    }

    public void setAuthId ( Integer authId ) {
        this.authId = authId;
    }

    public Integer getDataSourceId () {
        return dataSourceId;
    }

    public void setDataSourceId ( Integer dataSourceId ) {
        this.dataSourceId = dataSourceId;
    }

    public Integer getValidFlag () {
        return validFlag;
    }

    public void setValidFlag ( Integer validFlag ) {
        this.validFlag = validFlag;
    }

    public Integer getIsDefault () {
        return isDefault;
    }

    public void setIsDefault ( Integer isDefault ) {
        this.isDefault = isDefault;
    }

    public Date getCreatedDate () {
        return createdDate;
    }

    public void setCreatedDate ( Date createdDate ) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser () {
        return createdUser;
    }

    public void setCreatedUser ( String createdUser ) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedDate () {
        return updatedDate;
    }

    public void setUpdatedDate ( Date updatedDate ) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedUser () {
        return updatedUser;
    }

    public void setUpdatedUser ( String updatedUser ) {
        this.updatedUser = updatedUser;
    }

    @Override
    public String toString () {
        return "ProjectModel [projectId=" + projectId + ", projectCode=" + projectCode + ", projectName=" + projectName
                + ", accountId=" + accountId + ", authId=" + authId + ", dataSourceId=" + dataSourceId + ", validFlag="
                + validFlag + ", isDefault=" + isDefault + ", createdDate=" + createdDate + ", createdUser="
                + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + "]";
    }
    
    

}