/************************************************************************
 * 描述 ：数据库表t_datasource对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;
import java.util.List;

import com.yaic.auth.thirdparty.model.DataSourceModel;


/** 
* @ClassName: DataSourceDto 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:52:37 
*  
*/
public class DataSourceDto implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 4961658854908375032L;
	
	private Integer dataSourceId;
    /** 渠道代码 */
    private String dataSource;
    /** 渠道名称 */
    private String sourceName;
//    /** app_id */
//    private String appId;
    
    private AuthEncryptDto authEncryptDto;
    
    private AccountDto accountDto;
    
    private List<AuthMappingDto> authMappList;
    
    private List<CallbackUrlDto> callbackList;

    private List<ProjectDto> projectList;
    
	public DataSourceDto() {}
    
    public DataSourceDto(DataSourceModel model) {
    	if(model == null) model = new DataSourceModel();
    	this.dataSourceId = model.getDataSourceId();
    	this.dataSource = model.getDataSource();
    	this.sourceName = model.getSourceName();
	}

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

//	public String getAppId() {
//		return appId;
//	}
//
//	public void setAppId(String appId) {
//		this.appId = appId;
//	}

	public List<ProjectDto> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectDto> projectList) {
		this.projectList = projectList;
	}

	public AuthEncryptDto getAuthEncryptDto() {
		return authEncryptDto;
	}

	public void setAuthEncryptDto(AuthEncryptDto authEncryptDto) {
		this.authEncryptDto = authEncryptDto;
	}

	public AccountDto getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}

	public List<AuthMappingDto> getAuthMappList() {
		return authMappList;
	}

	public void setAuthMappList(List<AuthMappingDto> authMappList) {
		this.authMappList = authMappList;
	}

	public List<CallbackUrlDto> getCallbackList() {
		return callbackList;
	}

	public void setCallbackList(List<CallbackUrlDto> callbackList) {
		this.callbackList = callbackList;
	}
    
    

}
