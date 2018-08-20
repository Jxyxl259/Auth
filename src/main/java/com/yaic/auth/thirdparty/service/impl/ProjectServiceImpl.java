package com.yaic.auth.thirdparty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.thirdparty.dao.AuthEncryptDao;
import com.yaic.auth.thirdparty.dao.AuthMappingDao;
import com.yaic.auth.thirdparty.dao.CallbackUrlDao;
import com.yaic.auth.thirdparty.dao.ProjectDao;
import com.yaic.auth.thirdparty.dto.ProjectViewDto;
import com.yaic.auth.thirdparty.model.ProjectModel;
import com.yaic.auth.thirdparty.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceServiceimpl.class);

	@Autowired
	private ProjectDao projectMapper;
	@Autowired
	private AuthMappingDao authMappingMapper;
	@Autowired
	private CallbackUrlDao callbackUrlMapper;
	@Autowired
	private AuthEncryptDao authEncryptMapper;

	@Override
	public List<ProjectModel> getList(ProjectModel projectModel) throws Exception {
		List<ProjectModel> list = projectMapper.getList(projectModel);
		return list;
	}

	@Override
	public Integer addInfo(ProjectModel projectModel) throws Exception {
		// projectModel.setCreatedUser(Constants.LOGIN_USER_CODE());
		// projectModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		projectModel.setValidFlag(PublicParamters.VALID_FLAG_Y);
		return projectMapper.insertSelective(projectModel);
	}

	@Override
	public Integer updateInfo(ProjectModel projectModel) throws Exception {
		// projectModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		return projectMapper.updateByPrimaryKeySelective(projectModel);
	}

	@Override
	public Integer deleteInfo(Integer projectId) throws Exception {
		int result = 0;

		// 1、根据projectI判断方案是存在
		ProjectModel projectModel = projectMapper.selectOneByPrimaryKey(projectId);
		if (projectModel != null) {

			// 2、删除相关数据

			callbackUrlMapper.deleteByProjectId(projectId);
			authMappingMapper.deleteByProjectId(projectId);

			if (projectModel.getAuthId() != null)
				authEncryptMapper.deleteByPrimaryKey(projectModel.getAuthId());

			result = projectMapper.deleteByPrimaryKey(projectId);

		}

		return result;
	}

	@Override
	public ProjectModel getOneByProjectId(Integer projectId) throws Exception {
		return projectMapper.selectOneByPrimaryKey(projectId);
	}

	@Override
	public ProjectModel getOneByProjectCode(String projectCode) throws Exception {
		return projectMapper.selectOneByProjectCode(projectCode);
	}

	@Override
	public ProjectModel getOneProject(ProjectModel projectModel) throws Exception {
		List<ProjectModel> list = projectMapper.getList(projectModel);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer deleteInfoByIds(List<String> ids) throws Exception {
		int result = 0;
		if (ids.size() > 0) {
			logger.info("delete project by ids{}", ids);
			for (String projectIdTemp : ids) {
				Integer projectId = Integer.valueOf(projectIdTemp);
				result = this.deleteInfo(projectId);
			}
		}
		return result;
	}

	@Override
	public ProjectModel getDefaultProjectBySourceid(Integer dataSourceId) throws Exception {
		return projectMapper.getDefaultProjectBySourceid(dataSourceId);
	}

	@Override
	public List<ProjectViewDto> getProjectViewDto(ProjectViewDto projectViewDto) throws Exception {
		Map<String, String> parametersMap = new HashMap<>();
		if (StringUtils.isNotBlank(projectViewDto.getProjectCode())) {
			parametersMap.put("projectCode", projectViewDto.getProjectCode());
		}
		if (StringUtils.isNotBlank(projectViewDto.getProjectName())) {
			parametersMap.put("projectName", projectViewDto.getProjectName());
		}
		if (StringUtils.isNotBlank(projectViewDto.getIsDefault())) {
			parametersMap.put("isDefault", projectViewDto.getIsDefault());
		}
		List<ProjectViewDto> list = projectMapper.selectProjectView(parametersMap);
		return list;
	}

}
