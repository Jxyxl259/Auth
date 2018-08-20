package com.yaic.auth.thirdparty.model;

import java.io.Serializable;
import java.util.Date;

import com.yaic.auth.common.BaseDto;


/** 
* @ClassName: DataSourceModel 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:54:44 
*  
*/
public class DataSourceModel extends BaseDto implements Serializable {

    private static final long serialVersionUID = -7517993255432951980L;
    private Integer dataSourceId;
    /** 渠道代码 */
    private String dataSource;
    /** 渠道名称 */
    private String sourceName;
    /** app_id */
    private String appId;
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
    
    public DataSourceModel() {
		super();
	}

	public DataSourceModel(String appId) {
		this.appId = appId;
	}


	public DataSourceModel(int dataSourceId, String dataSource, String sourceName, String appId, int authId,
			Integer validFlag, Date createdDate, String createdUser, Date updatedDate, String updatedUser) {
		super();
		this.dataSourceId = dataSourceId;
		this.dataSource = dataSource;
		this.sourceName = sourceName;
		this.appId = appId;
		this.authId = authId;
		this.validFlag = validFlag;
		this.createdDate = createdDate;
		this.createdUser = createdUser;
		this.updatedDate = updatedDate;
		this.updatedUser = updatedUser;
	}

	public Integer getDataSourceId () {
        return dataSourceId;
    }

    public void setDataSourceId ( Integer dataSourceId ) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSource () {
        return dataSource;
    }

    public void setDataSource ( String dataSource ) {
        this.dataSource = dataSource;
    }

    public String getSourceName () {
        return sourceName;
    }

    public void setSourceName ( String sourceName ) {
        this.sourceName = sourceName;
    }

    public String getAppId () {
        return appId;
    }

    public void setAppId ( String appId ) {
        this.appId = appId;
    }

    public Integer getAuthId () {
        return authId;
    }

    public void setAuthId ( Integer authId ) {
        this.authId = authId;
    }

    public Integer getValidFlag () {
        return validFlag;
    }

    public void setValidFlag ( Integer validFlag ) {
        this.validFlag = validFlag;
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
        return "DataSourceModel [dataSourceId=" + dataSourceId + ", dataSource=" + dataSource + ", sourceName="
                + sourceName + ", appId=" + appId + ", authId=" + authId + ", validFlag=" + validFlag + ", createdDate="
                + createdDate + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser="
                + updatedUser + ", getDataSourceId()=" + getDataSourceId () + ", getDataSource()=" + getDataSource ()
                + ", getSourceName()=" + getSourceName () + ", getAppId()=" + getAppId () + ", getAuthId()="
                + getAuthId () + ", getValidFlag()=" + getValidFlag () + ", getCreatedDate()=" + getCreatedDate ()
                + ", getCreatedUser()=" + getCreatedUser () + ", getUpdatedDate()=" + getUpdatedDate ()
                + ", getUpdatedUser()=" + getUpdatedUser () + ", getPageSize()=" + getPageSize () + ", getPageNum()="
                + getPageNum () + ", getReqType()=" + getReqType () + ", getClass()=" + getClass () + ", hashCode()="
                + hashCode () + ", toString()=" + super.toString () + "]";
    }

}