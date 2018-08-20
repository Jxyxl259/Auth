package com.yaic.auth.thirdparty.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.thirdparty.dao.CallbackUrlDao;
import com.yaic.auth.thirdparty.dto.CallbackUrlViewDto;
import com.yaic.auth.thirdparty.model.CallbackUrlModel;
import com.yaic.auth.thirdparty.service.CallbackUrlService;

@Service
public class CallbackUrlServiceImpl implements CallbackUrlService {
	// private static final Logger logger =
	// LoggerFactory.getLogger(CallbackUrlServiceImpl.class);
	@Autowired
	private CallbackUrlDao callbackUrlMapper;

	@Override
	public List<CallbackUrlModel> getList(CallbackUrlModel callbackUrlModel) throws Exception {
		List<CallbackUrlModel> list = callbackUrlMapper.selectByConditions(callbackUrlModel);
		return list;
	}

	@Override
	public Integer addInfo(CallbackUrlModel callbackUrlModel) throws Exception {
		// callbackUrlModel.setCreatedUser(Constants.LOGIN_USER_CODE());
		// callbackUrlModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		callbackUrlModel.setValidFlag(PublicParamters.VALID_FLAG_Y);
		return callbackUrlMapper.insertSelective(callbackUrlModel);
	}

	@Override
	public Integer updateInfo(CallbackUrlModel callbackUrlModel) throws Exception {
		// callbackUrlModel.setUpdatedUser(Constants.LOGIN_USER_CODE());
		return callbackUrlMapper.updateByPrimaryKeySelective(callbackUrlModel);
	}

	@Override
	public Integer deleteInfo(Integer callbackUrlId) throws Exception {
		return callbackUrlMapper.deleteByPrimaryKey(callbackUrlId);

	}

	@Override
	public CallbackUrlModel getOneByCallbackUrlId(Integer callbackUrlId) throws Exception {
		return callbackUrlMapper.selectByPrimaryKey(callbackUrlId);
	}

	@Override
	public CallbackUrlModel getOneByCallbackUrl(CallbackUrlModel callbackUrlModel) throws Exception {
		List<CallbackUrlModel> list = callbackUrlMapper.selectByConditions(callbackUrlModel);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public CallbackUrlModel getOneByProjectIdAndCallbackMethod(Integer projectId, String callbackMethod) {
		return callbackUrlMapper.selectOneByProjectIdAndCallbackMethod(projectId, callbackMethod);
	}

	@Override
	public Integer deleteInfoByIds(List<String> ids) throws Exception {
		int result = 0;
		if (ids.size() > 0) {
			for (String callbackUrlIdTemp : ids) {
				Integer callbackUrlId = Integer.valueOf(callbackUrlIdTemp);
				result = this.deleteInfo(callbackUrlId);
			}
		}
		return result;
	}

	@Override
	public Integer deleteByProjectId(Integer projectId) throws Exception {
		return callbackUrlMapper.deleteByProjectId(projectId);
	}

	@Override
	public List<CallbackUrlViewDto> getCallbackUrlViewDto(CallbackUrlModel callbackUrlModel) {
		return callbackUrlMapper.selectCallbackView(callbackUrlModel);
	}

}
