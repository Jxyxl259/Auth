package com.yaic.auth.controller;

import com.alibaba.fastjson.JSON;
import com.yaic.auth.service.OAuthService;
import com.yaic.common.Constants;
import com.yaic.common.ResponseMessage;
import com.yaic.servicelayer.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取token,刷新token,获取接口列表功能
 */
@RestController
@RequestMapping("/sns")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final OAuthService oauthService;

    @Autowired
    public AuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    /**
     *
     * @param appId 对接账号
     * @param appSecret 鉴权参数
     * @param dataSource 渠道代码
     * @param projectCode 方案代码
     * @return 响应信息
     * @throws Exception 异常
     */
	@RequestMapping(value = "/oauth2/authorize")
	public ResponseMessage authorize(@RequestParam(value = "app_id",required = false) String appId,
                                     @RequestParam(value = "app_secret",required = false) String appSecret,
                                     @RequestParam(value = "data_source",required = false) String dataSource,
                                     @RequestParam(value = "agrt_code",required = false) String projectCode)throws Exception {

		ResponseMessage responseMessage = new ResponseMessage();

		StringBuffer checkMsg = new StringBuffer();
		if (StringUtil.isEmpty(appId))
			checkMsg.append("app_id  ");
		if (StringUtil.isEmpty(appSecret))
			checkMsg.append("app_secret  ");
        if (checkMessage(responseMessage, checkMsg)) {
            return responseMessage;
        }

		logger.info("authorize_params:app_id[{}],appSecret[{}],dataSource[{}],projectCode[{}]", appId, appSecret, dataSource, projectCode);

		responseMessage = oauthService.getToken(appId, appSecret, dataSource, projectCode);
		logger.info("responseMessage:{}", JSON.toJSONString(responseMessage));
		return responseMessage;
	}

    /**
     *
     * @param appId 对接账号
     * @param appSecret 鉴权参数
     * @param refreshToken 刷新token参数
     * @param dataSource 渠道代码
     * @param projectCode 方案代码
     * @return 响应信息
     * @throws Exception 异常
     */
    @RequestMapping("/oauth2/token_refresh")
	public ResponseMessage token_refresh(@RequestParam(value = "app_id",required = false) String appId,
                                         @RequestParam(value = "app_secret",required = false) String appSecret,
                                         @RequestParam(value = "refresh_token",required = false) String refreshToken,
                                         @RequestParam(value = "data_source",required = false) String dataSource,
                                         @RequestParam(value = "agrt_code",required = false) String projectCode) throws Exception {

		ResponseMessage responseMessage = new ResponseMessage();

		StringBuffer checkMsg = new StringBuffer();
		if (StringUtil.isEmpty(appId))
			checkMsg.append("app_id  ");
		if (StringUtil.isEmpty(appSecret))
			checkMsg.append("app_secret  ");
		if (StringUtil.isEmpty(refreshToken))
			checkMsg.append("refresh_token  ");
        if (checkMessage(responseMessage, checkMsg)) {
            return responseMessage;
        }
		
		logger.info("authorize_params:appId[{}],refreshToken[{}],dataSource[{}],projectCode[{}]", appId, refreshToken, dataSource, projectCode);
		
		responseMessage = oauthService.refreshToken(appId, appSecret, refreshToken);
        logger.info("responseMessage:{}", JSON.toJSONString(responseMessage));
		return responseMessage;
	}

    /**
     *
     * @param openId 请求参数
     * @param accessToken 请求参数
     * @param dataSource 请求参数
     * @param projectCode 请求参数
     * @return 响应信息
     * @throws Exception 响应异常
     */
    @RequestMapping("/oauth2/token_auth")
	public ResponseMessage token_auth(@RequestParam(value = "open_id",required = false) String openId,
                                      @RequestParam(value = "access_token",required = false) String accessToken,
                                      @RequestParam(value = "data_source",required = false) String dataSource,
                                      @RequestParam(value = "agrt_code",required = false) String projectCode) throws Exception {
	
		ResponseMessage responseMessage = new ResponseMessage();
		
		StringBuffer checkMsg = new StringBuffer();
		if (StringUtil.isEmpty(accessToken)) {
			checkMsg.append("access_token,");
		}
		if (StringUtil.isEmpty(openId)) {
			checkMsg.append("open_id,");
		}

		if (checkMessage(responseMessage, checkMsg)) {
			return responseMessage;
		}

		logger.info("openId[{}],accessToken[{}],dataSource[{}],proejctCode[{}]",openId,accessToken,dataSource,projectCode);

        responseMessage = oauthService.token_auth(accessToken, openId);
        logger.info("responseMessage:{}", JSON.toJSONString(responseMessage));
        return responseMessage;
    }

    /**
     *
     * @param openId 请求参数
     * @param accessToken 请求参数
     * @param dataSource 请求参数
     * @param projectCode 请求参数
     * @return 响应信息
     * @throws Exception 响应异常
     */
    @RequestMapping("/oauth2/interface_list")
	public ResponseMessage interface_list(@RequestParam(value = "open_id",required = false) String openId,
                                          @RequestParam(value = "access_token",required = false) String accessToken,
                                          @RequestParam(value = "data_source",required = false) String dataSource,
                                          @RequestParam(value = "agrt_code",required = false) String projectCode) throws Exception {

		ResponseMessage responseMessage = new ResponseMessage();

		StringBuffer checkMsg = new StringBuffer();
		if (StringUtil.isEmpty(accessToken)) {
			checkMsg.append("access_token,");
		}
		if (StringUtil.isEmpty(openId)) {
			checkMsg.append("openId,");
		}

		if (checkMessage(responseMessage, checkMsg)) {
		    return responseMessage;
        }

        logger.info("openId[{}],accessToken[{}],dataSource[{}],proejctCode[{}]",openId,accessToken,dataSource,projectCode);

        responseMessage = oauthService.interface_list(accessToken,openId);
        logger.info("responseMessage:{}", JSON.toJSONString(responseMessage));
        return responseMessage;
	}

	private boolean checkMessage(ResponseMessage responseMessage, StringBuffer checkMsg) {
		if (StringUtil.isNotEmpty(checkMsg.toString())) {
			responseMessage.setState(Constants.STATE_FAIL);
			responseMessage.setMessage(checkMsg.substring(0, checkMsg.length() - 1) + " can not be empty");
			responseMessage.setCode(Constants.CODE_40006);
			return true;
		}
		return false;
	}

}
