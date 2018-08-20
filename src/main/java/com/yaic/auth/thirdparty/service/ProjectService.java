package com.yaic.auth.thirdparty.service;

import java.util.List;

import com.yaic.auth.thirdparty.dto.ProjectViewDto;
import com.yaic.auth.thirdparty.model.ProjectModel;


/** 
* @ClassName: ProjectService 
* @Description: 方案接口
* @author 
* @date 2018年6月17日 下午10:02:07 
*  
*/
public interface ProjectService {
	
	/** 
	* @Title: getList 
	* @Description: 根据projectModel查询得到ProjectModel集合
	* @param projectModel
	* @return    
	* @return List<ProjectModel>  
	* @throws 
	*/
	public List<ProjectModel> getList(ProjectModel projectModel) throws Exception;

	/** 
	* @Title: getOneProject 
	* @Description:根据projectModel得到ProjectModel集合，取出第一个，
	* 一般已知projectModel条件唯一时，用此方法
	* @param projectModel
	* @return    
	* @return ProjectModel  
	* @throws 
	*/
	public ProjectModel getOneProject(ProjectModel projectModel) throws Exception;


	/** 
	* @Title: addInfo 
	* @Description: 添加ProjectModel
	* @param projectModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer addInfo(ProjectModel projectModel) throws Exception;

	/** 
	* @Title: updateInfo 
	* @Description: 更新ProjectModel
	* @param projectModel
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer updateInfo(ProjectModel projectModel) throws Exception;

	/** 
	* @Title: deleteInfo 
	* @Description: 根据主键projectId删除ProjectModel
	* @param projectId
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfo(Integer projectId) throws Exception;

	/** 
	* @Title: getOneByProjectId 
	* @Description: 根据主键projectId得到ProjectModel
	* @param projectId
	* @return    
	* @return ProjectModel  
	* @throws 
	*/
	public ProjectModel getOneByProjectId(Integer projectId) throws Exception;

	/** 
	* @Title: getOneByProjectCode 
	* @Description: 根据projectCode得到ProjectModel
	* @param projectCode
	* @return    
	* @return ProjectModel  
	* @throws 
	*/
	public ProjectModel getOneByProjectCode(String projectCode) throws Exception;
	
	/** 
	* @Title: deleteInfoByIds 
	* @Description: 批量删除
	* @param ids
	* @return    
	* @return Integer  
	* @throws 
	*/
	public Integer deleteInfoByIds(List<String> ids) throws Exception;

	/** 
	* @Title: getDefaultProjectBySourceid 
	* @Description: 根据渠道编号获取渠道的默认方案信息
	* @param dataSourceId
	* @return    
	* @return ProjectModel  
	* @throws 
	*/
	public ProjectModel getDefaultProjectBySourceid(Integer dataSourceId) throws Exception;
	
	/** 
	* @Title: getProjectViewDto
	* @Description: 根据前台传过来projectViewDto获取projectViewDto，主要负责前台展示
	* @param ProjectViewDto
	* @return    
	* @return List<ProjectViewDto>
	* @throws 
	*/
	public List<ProjectViewDto> getProjectViewDto(ProjectViewDto projectViewDto) throws Exception;
}
