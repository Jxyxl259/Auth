package com.yaic.auth.thirdparty.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yaic.auth.thirdparty.dto.AuthMappingDto;
import com.yaic.auth.thirdparty.dto.InterfaceInfoDto;
import com.yaic.auth.thirdparty.model.AuthMappingModel;

public interface AuthMappingDao {
	int deleteByPrimaryKey(Integer mappingId);

	int deleteByProjectId(Integer projectId);

	int deleteByServerId(Integer serverId);

	int insert(AuthMappingModel authMappingModel);

	int insertSelective(AuthMappingModel authMappingModel);

	AuthMappingModel selectByPrimaryKey(Integer mappingId);

	int updateByPrimaryKeySelective(AuthMappingModel authMappingModel);

	int updateByPrimaryKey(AuthMappingModel authMappingModel);

	List<AuthMappingModel> selectByConditions(AuthMappingModel authMappingModel);

	int selectByProjectId(Integer projectId);

	List<AuthMappingModel> selectByAppIdAndReqUrl(@Param("appId") String appId, @Param("requestUrl") String requestUrl);

	AuthMappingModel selectOneByProjectIdAndServerId(@Param("projectId") Integer projectId,
			@Param("serverId") Integer serverId);

	AuthMappingModel selectOneByRequestTypeAndRequestUrl(@Param("requestType") String requestType,
			@Param("requestUrl") String requestUrl);
	
	List<InterfaceInfoDto> selectListsByAppid(String appid);

	List<AuthMappingDto> getMappAndServerByProId(Integer projectId);
	
	List<Map<String,Object>> selectByProjectDataSource(Map<String,String> paramMap);
	
	List<Map<String,Object>> selectByProjectDataSourceOriginal(Map<String,String> paramMap);

    List<AuthMappingModel> getMappingByAppId(String appid);
}