package com.yaic.auth.thirdparty.dao;

import java.util.List;

import com.yaic.auth.thirdparty.model.AuthEncryptModel;

public interface AuthEncryptDao {
	int deleteByPrimaryKey(Integer authId);

	int insert(AuthEncryptModel authEncryptModel);

	int insertSelective(AuthEncryptModel authEncryptModel);

	AuthEncryptModel selectByPrimaryKey(Integer authId);

	int updateByPrimaryKeySelective(AuthEncryptModel authEncryptModel);

	int updateByPrimaryKey(AuthEncryptModel authEncryptModel);

	List<AuthEncryptModel> selectByConditions(AuthEncryptModel authEncryptModel);
	
	
	List<AuthEncryptModel> selectListsByAppid(String appid);

}