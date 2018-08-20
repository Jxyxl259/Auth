package com.yaic.auth.thirdparty.dao;

import java.util.List;
import java.util.Map;

import com.yaic.auth.thirdparty.dto.ProjectViewDto;
import com.yaic.auth.thirdparty.model.ProjectModel;

public interface ProjectDao {
	int deleteByPrimaryKey(Integer projectId);

	int insert(ProjectModel projectModel);

	int insertSelective(ProjectModel projectModel);

	ProjectModel selectOneByPrimaryKey(Integer projectId);

	ProjectModel selectOneByProjectCode(String projectCode);

	int updateByPrimaryKeySelective(ProjectModel projectModel);

	int updateByPrimaryKey(ProjectModel projectModel);

	List<ProjectModel> getList(ProjectModel projectModel);

	ProjectModel getDefaultProjectBySourceid(Integer dataSourceId);

	List<ProjectViewDto> selectProjectView(Map<String, String> parametersMap);

}