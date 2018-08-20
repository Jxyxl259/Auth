package com.yaic.auth.thirdparty.dto;

public class InterfaceInfoDto  {

	private String dataSourceCode;
	private String projectCode;
	private String requestUrl;
//	private Integer projectId;
//	private Integer mappingId;
//	private Integer dataSourceId;
	
	public String getDataSourceCode() {
		return dataSourceCode;
	}
	public void setDataSourceCode(String dataSourceCode) {
		this.dataSourceCode = dataSourceCode;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
//	public Integer getProjectId() {
//		return projectId;
//	}
//	public void setProjectId(Integer projectId) {
//		this.projectId = projectId;
//	}
//	public Integer getMappingId() {
//		return mappingId;
//	}
//	public void setMappingId(Integer mappingId) {
//		this.mappingId = mappingId;
//	}
//	public Integer getDataSourceId() {
//		return dataSourceId;
//	}
//	public void setDataSourceId(Integer dataSourceId) {
//		this.dataSourceId = dataSourceId;
//	}


}