package com.yaic.auth.thirdparty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaic.auth.thirdparty.model.AuthTokenModel;

public interface AuthTokenDao {
	int deleteByPrimaryKey(Integer tokenId);

	AuthTokenModel selectByPrimaryKey(Integer tokenId);

	int insert(AuthTokenModel authTokenModel);

	int insertSelective(AuthTokenModel authTokenModel);

	List<AuthTokenModel> selectByConditions(AuthTokenModel authTokenModel);

	int updateByAppIdSelective(AuthTokenModel authTokenModel);

	AuthTokenModel selectOneByAppIdAndAppSecret(@Param("appId") String appId, @Param("appSecret") String appSecret);

}