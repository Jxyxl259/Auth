package com.yaic.auth.thirdparty.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yaic.auth.thirdparty.dto.CallbackUrlViewDto;
import com.yaic.auth.thirdparty.model.CallbackUrlModel;

public interface CallbackUrlDao {
	int deleteByPrimaryKey(Integer callbackUrlId);

	int deleteByProjectId(Integer projectId);

	int insertSelective(CallbackUrlModel callbackUrlModel);

	CallbackUrlModel selectByPrimaryKey(Integer callbackUrlId);

	int updateByPrimaryKeySelective(CallbackUrlModel callbackUrlModel);

	List<CallbackUrlModel> selectByConditions(CallbackUrlModel callbackUrlModel);

	// int selectByProjectId(Integer projectId);

	List<CallbackUrlViewDto> selectCallbackView(CallbackUrlModel callbackUrlModel);
	
	CallbackUrlModel selectOneByProjectIdAndCallbackMethod(@Param("projectId") Integer projectId,
			@Param("callbackMethod") String callbackMethod);

	/**
	 * 根据方案号跟渠道代号查询第三方回调地址
	 * @param appId 		APP_ID
	 * @param projectCode   方案代码
	 * @param callbackType  回调类型
	 * @return 第三方回调地址
	 */
	String selectCallBackUrlByAppIdAndProjectCodeAndCallBackType(@Param("appId") String appId,
																 @Param("projectCode") String projectCode,
																 @Param("callbackType") String callbackType);
}