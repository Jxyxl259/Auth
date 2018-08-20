package com.yaic.auth.thirdparty.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.thirdparty.dao.AuthEncryptDao;
import com.yaic.auth.thirdparty.dao.AuthMappingDao;
import com.yaic.auth.thirdparty.dao.CallbackUrlDao;
import com.yaic.auth.thirdparty.dao.DataSourceDao;
import com.yaic.auth.thirdparty.dao.ProjectDao;
import com.yaic.auth.thirdparty.dto.DataSourceViewDto;
import com.yaic.auth.thirdparty.model.DataSourceModel;
import com.yaic.auth.thirdparty.model.ProjectModel;
import com.yaic.auth.thirdparty.service.DataSourceService;

@Service
public class DataSourceServiceimpl implements DataSourceService {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceServiceimpl.class);
	@Autowired
	private DataSourceDao dataSourceMapper;
	@Autowired
	private ProjectDao projectMapper;
	@Autowired
	private AuthMappingDao authMappingMapper;
	@Autowired
	private CallbackUrlDao callbackUrlMapper;
	@Autowired
	private AuthEncryptDao authEncryptMapper;

	@Override
	public List<DataSourceModel> getList(DataSourceModel dataSourceModel) throws Exception{
		List<DataSourceModel> list = dataSourceMapper.getLists(dataSourceModel);
		return list;
	}

	@Override
	public Integer addInfo(DataSourceModel dataSourceModel) throws Exception {
//		dataSourceModel.setCreatedUser(Constants.LOGIN_USER_CODE());
//		dataSourceModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		dataSourceModel.setValidFlag(PublicParamters.VALID_FLAG_Y);
		int resu = dataSourceMapper.insertSelective(dataSourceModel);
		return resu;
	}

	@Override
	public Integer updateInfo(DataSourceModel dataSourceModel) throws Exception {
//		dataSourceModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		int resu = dataSourceMapper.updateByPrimaryKeySelective(dataSourceModel);
		return resu;
	}

	@Override
	public Integer deleteInfo(Integer dataSourceId) throws Exception {
		int result = 0;

		// 1.根据dataSourceId查渠道表，判断渠道是否存在
		DataSourceModel dataSourceModel = dataSourceMapper.selectOneByPrimaryKey(dataSourceId);

		if (dataSourceModel != null) {

			// 2.根据dataSourceId查方案表，得到projectId和authId
			List<ProjectModel> projectLists = projectMapper.getList(new ProjectModel(dataSourceId));

			if (projectLists.size() > 0) {

				// 4.更新删除
				for (ProjectModel model : projectLists) {

					callbackUrlMapper.deleteByProjectId(model.getProjectId());
					authMappingMapper.deleteByProjectId(model.getProjectId());

					if (model.getAuthId() != null)
						authEncryptMapper.deleteByPrimaryKey(model.getAuthId());

					projectMapper.deleteByPrimaryKey(model.getProjectId());
				}
			}

			int i = dataSourceMapper.deleteByPrimaryKey(dataSourceId);

			if (i > 0)
				result = i;
		}
		return result;
	}

	@Override
	public DataSourceModel getOneByDataSourceCode(String dataSourceCode) throws Exception {
		return dataSourceMapper.selectOneByDataSource(dataSourceCode);
	}

	@Override
	public DataSourceModel getOneByDataSourceId(Integer dataSourceId) throws Exception {
		return dataSourceMapper.selectOneByPrimaryKey(dataSourceId);
	}

	@Override
	public DataSourceModel getOneByDataSource(DataSourceModel dataSourceModel) throws Exception {
		List<DataSourceModel> list = this.getList(dataSourceModel);
		if (list.size() > 0) {
			logger.debug("if list.size:{} > 0 ,get the first record", list.size());
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer deleteInfoByIds(List<String> ids) throws Exception {
		int result = 0;
		if (ids.size() > 0) {
			for (String dataSourceIdTemp : ids) {
				Integer dataSourceId = Integer.valueOf(dataSourceIdTemp);
				result = this.deleteInfo(dataSourceId);
			}
		}
		return result;
	}

	@Override
	public List<DataSourceViewDto> getDataSourceView(DataSourceModel dataSourceModel) throws Exception {
		// TODO Auto-generated method stub
		return dataSourceMapper.selectDataSourceView(dataSourceModel);
	}

}
