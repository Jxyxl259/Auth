package com.yaic.auth.thirdparty.dao;

import java.util.List;

import com.yaic.auth.thirdparty.dto.DataSourceViewDto;
import com.yaic.auth.thirdparty.model.DataSourceModel;

public interface DataSourceDao {
	int deleteByPrimaryKey(Integer dataSourceId);

	int insert(DataSourceModel dataSourceModel);

	int insertSelective(DataSourceModel dataSourceModel);

	DataSourceModel selectOneByPrimaryKey(Integer dataSourceId);

	int updateByPrimaryKeySelective(DataSourceModel dataSourceModel);

	int updateByPrimaryKey(DataSourceModel dataSourceModel);

	List<DataSourceModel> getLists(DataSourceModel dataSourceModel);

	DataSourceModel selectOneByDataSource(String dataSource);
	
	List<DataSourceViewDto> selectDataSourceView(DataSourceModel dataSourceModel);

}