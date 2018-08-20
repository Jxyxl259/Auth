package com.yaic.auth.thirdparty.dao;

import com.yaic.auth.thirdparty.model.AuthCallbackInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthCallbackInfoDao {


    int deleteByPrimaryKey(Integer id);

    int insert(AuthCallbackInfoModel record);

    int insertSelective(AuthCallbackInfoModel record);

    AuthCallbackInfoModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthCallbackInfoModel record);

    int updateByPrimaryKeyWithBLOBs(AuthCallbackInfoModel record);

    int updateByPrimaryKey(AuthCallbackInfoModel record);

	/**
	 * 件查询存在的条目数量
	 * @param paramMap
	 * @return
	 */
	int countExistItemSelective(Map<String,Object> paramMap);

	/**
	 * 查询未能回调成功的信息
	 * @param  paramMap
	 * 			key:dealType(处理类型) value:01:处理支付成功回调  11:处理退保回调
	 * @return List<AuthCallbackInfoModel>
	 */
	List<AuthCallbackInfoModel> selectUnsuccessCallbackInfo(Map<String,Object> paramMap);
}