package com.yaic.auth.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.yaic.auth.thirdparty.dao.AccountDao;
import com.yaic.auth.thirdparty.dao.AuthCallbackInfoDao;
import com.yaic.auth.thirdparty.dao.CallbackUrlDao;
import com.yaic.auth.thirdparty.dto.AuthCallbackInfoDto;
import com.yaic.auth.thirdparty.dto.CallbackInfoDto;
import com.yaic.auth.thirdparty.model.AccountModel;
import com.yaic.auth.thirdparty.model.AuthCallbackInfoModel;
import com.yaic.auth.thirdparty.service.CallbackService;
import com.yaic.common.Constants;
import com.yaic.common.InvokeResultDto;
import com.yaic.common.StatusCodeProvider;
import com.yaic.common.util.HttpUtil;
import com.yaic.servicelayer.http.wrapper.HttpResponseWrapper;
import com.yaic.servicelayer.util.ObjectUtil;
import com.yaic.servicelayer.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.yaic.auth.common.PublicParamters.CALL_BACK_TYPE_CANCEL_INSURANCE_CALLBACK;
import static com.yaic.auth.common.PublicParamters.CALL_BACK_TYPE_PAY_CALLBACK;

/**
 * @Description: 支付成功后，第一次回调第三方
 * @author: jiangxy
 * @date: 2018\7\25 0025 12:54
 */

@Service
public class CallbackServiceImpl implements CallbackService {

	private Logger log = LoggerFactory.getLogger(CallbackServiceImpl.class);

	private static final String FAIL_CODE = "9999";
	private static final String SUCCESS_CODE = "0000";
	/** OPEN返回状态码前缀 */
	private static final String STATUSCODE_PREFIX = StatusCodeProvider.SYSTEMNO_INTERFACE_OPEN;


	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AuthCallbackInfoDao authCallbackInfoDao;

	@Autowired
	private CallbackUrlDao callbackUrlDao;

	@Override
	public String dealBiz(String content) {
		log.info("接口处理开始");
		InvokeResultDto resultDto = new InvokeResultDto();

		AuthCallbackInfoDto authCallbackInfoDto = new AuthCallbackInfoDto();
		CallbackInfoDto callbackInfoDto = null;
		try {
			callbackInfoDto = JSON.parseObject(content, CallbackInfoDto.class);
		} catch (Exception e) {
			resultDto.setMessage("处理失败,请求内容体解析异常");
			resultDto.setCode(FAIL_CODE);
			resultDto.setStatusCode(STATUSCODE_PREFIX + FAIL_CODE);
			log.info("接口处理失败, resultDto={}", JSON.toJSONString(resultDto));
			return JSON.toJSONString(resultDto);
		}

		if (StringUtils.isEmpty(callbackInfoDto.getContent())) {
			resultDto.setMessage("处理失败,请求内容体不能为空");
			resultDto.setCode(FAIL_CODE);
			resultDto.setStatusCode(STATUSCODE_PREFIX + FAIL_CODE);
			log.info("接口处理失败, resultDto={}", JSON.toJSONString(resultDto));
			return JSON.toJSONString(resultDto);
		}

		BeanUtils.copyProperties(callbackInfoDto, authCallbackInfoDto);
		authCallbackInfoDto.setProjectCode(callbackInfoDto.getAgrtCode());

		StringBuilder checkMsg = new StringBuilder();

		if (StringUtil.isEmpty(authCallbackInfoDto.getAppId())) {
			checkMsg.append("appId不能为空,");
		}
		if (StringUtil.isEmpty(authCallbackInfoDto.getDealType())) {
			checkMsg.append("处理类型不能为空,");
		}
		if (StringUtil.isEmpty(authCallbackInfoDto.getContent())) {
			checkMsg.append("处理内容不能为空,");
		}
		if (StringUtil.isNotEmpty(checkMsg.toString())) {
			resultDto.setMessage(checkMsg.substring(0, checkMsg.length() - 1));
			resultDto.setCode(FAIL_CODE);
			resultDto.setStatusCode(STATUSCODE_PREFIX + FAIL_CODE);
			log.info("接口处理失败, resultDto={}", JSON.toJSONString(resultDto));
			return JSON.toJSONString(resultDto);
		}

		AccountModel account = accountDao.getOneByAppid(authCallbackInfoDto.getAppId());

		if (account == null) {
			resultDto.setMessage("不存在该APPID");
			resultDto.setCode(FAIL_CODE);
			resultDto.setStatusCode(STATUSCODE_PREFIX + FAIL_CODE);
			log.info("接口处理失败, resultDto={}", JSON.toJSONString(resultDto));
			return JSON.toJSONString(resultDto);
		}

		// 支付回调信息优化
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("appId",authCallbackInfoDto.getAppId());
		paramMap.put("projectCode",authCallbackInfoDto.getProjectCode());
		paramMap.put("dataSource",authCallbackInfoDto.getDataSource());
		paramMap.put("businessNo",authCallbackInfoDto.getBusinessNo());
		paramMap.put("dealType",authCallbackInfoDto.getDealType());
		paramMap.put("dealStatus",Constants.DEAL_BIZ_SUCCESS);
		int rows = authCallbackInfoDao.countExistItemSelective(paramMap);

		if (rows == 0) {
			// 获取回调url, 新鉴权回调url最终是与方案代号（project_code）一一对应的
			String appId = authCallbackInfoDto.getAppId();
			String projectCode = authCallbackInfoDto.getProjectCode();
			String callbackType = transDealType2CallBackType(authCallbackInfoDto.getDealType());
			String callbackUrl = callbackUrlDao.selectCallBackUrlByAppIdAndProjectCodeAndCallBackType(appId, projectCode, callbackType);

			if (StringUtil.isNotEmpty(callbackUrl)) {
				// 先插入,再请求,最后更新状态
				AuthCallbackInfoModel callbackInfoModel = new AuthCallbackInfoModel();
				ObjectUtil.copyProperties(callbackInfoModel, authCallbackInfoDto);
				callbackInfoModel.setInvalidFlag(0);
				callbackInfoModel.setCreatedUser(Constants.CREATED_USER);
				callbackInfoModel.setCreatedDate(new Date());
				callbackInfoModel.setUpdatedUser(Constants.UPDATED_USER);
				callbackInfoModel.setUpdatedDate(new Date());
				// 设置处理状态为处理中
				callbackInfoModel.setDealStatus(Constants.DEAL_BIZ_PROCESS);
				callbackInfoModel.setDealCount(0);
				callbackInfoModel.setCallbackUrl(callbackUrl);
				authCallbackInfoDao.insertSelective(callbackInfoModel);


				HttpResponseWrapper result = HttpUtil.connectServer(callbackInfoDto.getContent(), callbackUrl, null, null, null);
				if (result.getStatus()) {
                    // 回调成功（趣店回调返回成功值特殊处理）
					if (Constants.SUCCESS_MSG.equals((String)result.getContent())
                            || Constants.QUDIAN_SUCCESS_CODE.equals(JSON.parseObject((String)result.getContent(), Map.class).get("code"))) {
						callbackInfoModel.setDealStatus(Constants.DEAL_BIZ_SUCCESS);
						callbackInfoModel.setDealCount(1);
					} else {
						callbackInfoModel.setDealStatus(Constants.DEAL_BIZ_FAILURE);
						callbackInfoModel.setDealCount(1);
					}
				} else {
					callbackInfoModel.setDealStatus(Constants.DEAL_BIZ_FAILURE);
					callbackInfoModel.setDealCount(1);
				}
				authCallbackInfoDao.updateByPrimaryKeySelective(callbackInfoModel);
			}
		}
		resultDto.setMessage("SUCCESS");
		resultDto.setCode(SUCCESS_CODE);
		resultDto.setStatusCode(SUCCESS_CODE);
		log.info("接口处理成功, resultDto={}", JSON.toJSONString(resultDto));
		return JSON.toJSONString(resultDto);
	}


	/**
	 * 将处理类型转换为回调类型
	 * @param dealType
	 * @return
	 */
	private String transDealType2CallBackType(String dealType) {

		String callbackType = null;

		switch(dealType){
			case "01" : callbackType =  CALL_BACK_TYPE_PAY_CALLBACK; break;
			case "11" : callbackType =  CALL_BACK_TYPE_CANCEL_INSURANCE_CALLBACK; break;
		}

		return callbackType;
	}


}
