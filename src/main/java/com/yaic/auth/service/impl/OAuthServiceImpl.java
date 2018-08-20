package com.yaic.auth.service.impl;

import java.util.*;

import com.yaic.auth.common.init.dao.AuthPubCodeDao;
import com.yaic.auth.thirdparty.model.ProjectModel;
import com.yaic.auth.thirdparty.service.*;
import com.yaic.servicelayer.util.CollectionUtil;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.common.UuidUtils;
import com.yaic.auth.common.init.model.AuthPubCodeModel;
import com.yaic.auth.service.OAuthService;
import com.yaic.auth.thirdparty.dto.InterfaceInfoDto;
import com.yaic.auth.thirdparty.model.AccountModel;
import com.yaic.auth.thirdparty.model.AuthEncryptModel;
import com.yaic.auth.thirdparty.model.AuthTokenModel;
import com.yaic.auth.util.ConfigHelper;
import com.yaic.auth.util.ToolsUtils;
import com.yaic.common.Constants;
import com.yaic.common.RespBodyData;
import com.yaic.common.ResponseMessage;
import com.yaic.servicelayer.util.StringUtil;

@Service
public class OAuthServiceImpl implements OAuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(OAuthServiceImpl.class);
    private static String tokenExpireTime = ConfigHelper.getConfigValue("token.expire.time");
    private static String refreshTokenExpireTime = ConfigHelper.getConfigValue("refresh.token.expire.time");

    private final AccountService accountService;

    private final AuthMappingService authMappService;

    private final AuthTokenService authTokenService;

    private final AuthEncryptService authEncryptService;
    
    private final AuthPubCodeDao authPubCodeDao;

    private final ProjectService projectService;
    

    @Autowired
    public OAuthServiceImpl(AccountService accountService, AuthMappingService authMappService,
    		AuthTokenService authTokenService, AuthEncryptService authEncryptService,
    		AuthPubCodeDao authPubCodeDao, ProjectService projectService) {
        this.accountService = accountService;
        this.authMappService = authMappService;
        this.authTokenService = authTokenService;
        this.authEncryptService = authEncryptService;
        this.authPubCodeDao = authPubCodeDao;
        this.projectService = projectService;
    }

    static {
        if(!StringUtil.isInteger(tokenExpireTime)) {
            logger.warn("token.expire.time is invalid.");
            tokenExpireTime = "86400";
        }
        if(!StringUtil.isInteger(refreshTokenExpireTime)) {
            logger.warn("token.expire.time is invalid.");
            refreshTokenExpireTime = "5184000";
        }
    }

    @Override
	public ResponseMessage getToken(String appId, String appSecret, String dataSource, String projectCode) throws Exception{
		
		ResponseMessage responseMessage = new ResponseMessage();

		// 获取账户信息
		AccountModel accountModel = accountService.getOneByAppid(appId);
		
		// 判断账户信息
        logger.info("check account.");
        if (checkAccount(appId, responseMessage, accountModel)) {
            return responseMessage;
        }

        ProjectModel projectModel = new ProjectModel();
        ProjectModel oneProject = null;
        AuthEncryptModel authEncryptModel = new AuthEncryptModel();
		authEncryptModel.setAuthType(PublicParamters.AUTH_TYPE_COMMON_AUTH);
		authEncryptModel.setValidFlag(PublicParamters.VALID_FLAG_Y);
        List<AuthEncryptModel> encryList = new ArrayList<>();

        if(StringUtil.isNotEmpty(projectCode) || StringUtil.isNotEmpty(dataSource)) {
            if(StringUtil.isNotEmpty(dataSource)) {
                projectModel.setIsDefault(1);
                projectModel.setProjectCode(dataSource);
                oneProject = projectService.getOneProject(projectModel);
                authEncryptModel.setAuthId(oneProject.getAuthId());
                List<AuthEncryptModel> encryList1 = authEncryptService.getList(authEncryptModel);
                encryList = encryList1;
                logger.info("query auth_encrypt by dataSource,count[{}]",encryList.size());
            }
            if(StringUtil.isNotEmpty(projectCode)) {
                projectModel.setIsDefault(0);
                projectModel.setProjectCode(projectCode);
                oneProject = projectService.getOneProject(projectModel);
                authEncryptModel.setAuthId(oneProject.getAuthId());
                List<AuthEncryptModel> encryList2 = authEncryptService.getList(authEncryptModel);
                encryList.addAll(encryList2);
                logger.info("query auth_encrypt by project,count[{}]",encryList.size());
            }
        }

        authEncryptModel.setAuthId(null);
        authEncryptModel.setAppId(appId);
        List<AuthEncryptModel> encryList3 = authEncryptService.getList(authEncryptModel);
        encryList3 = authEncryptService.getList(authEncryptModel);
        encryList.addAll(encryList3);
        logger.info("query auth_encrypt by appId,count[{}]",encryList.size());

		if (ToolsUtils.isEmpty(encryList)) {
			logger.warn("can not find encrypt info, appid:{}.", appId);
            responseMessage.setBaseMsg(Constants.CODE_50010,Constants.STATE_FAIL,Constants.MSG_50010);
			return responseMessage;
		}

		boolean flag = false;
        for (AuthEncryptModel model : encryList) {
            if (appSecret.equals(model.getAuthParam1())) {
                flag = true;
                break;
            } else if (appSecret.equals(model.getAuthParam2())){
                flag = true;
                break;
            }
        }

		// 可能存在多个,所有app_secret中没有比配到则为没有匹配上
		if (!flag) {
			logger.warn("app_secret {} is not equal to db_secret.", appSecret);
            responseMessage.setBaseMsg(Constants.CODE_40002,Constants.STATE_FAIL,Constants.MSG_40002);
			return responseMessage;
		}
		
		// 获取token信息
		AuthTokenModel authTokenModel = authTokenService.getOneByAppIdAndAppSecret(appId, appSecret);
		
		if (authTokenModel == null) {

		    logger.info("authTokenModel is null,first auth.");

            DateTime nowTime = new DateTime();

            String openId = UuidUtils.getUuid();
            String accessToken = UuidUtils.getUuid();
            String refreshToken = UuidUtils.getUuid();

            DateTime expirationTime = nowTime.plusSeconds(Integer.parseInt(tokenExpireTime));
            DateTime refreshExpirationTime = nowTime.plusSeconds(Integer.parseInt(refreshTokenExpireTime));

            RespBodyData respBodyData = new RespBodyData();
            respBodyData.setAccess_token(accessToken);
            respBodyData.setRefresh_token(refreshToken);
            respBodyData.setExpires_in((long) (Seconds.secondsBetween(new DateTime(), expirationTime).getSeconds()));
            respBodyData.setOpen_id(openId);
            responseMessage.setData(respBodyData);
            responseMessage.setBaseMsg(Constants.CODE_SUCCESS, Constants.STATE_SUCCESS, Constants.MSG_SUCCESS);

            authTokenModel = new AuthTokenModel();
            authTokenModel.setAccessToken(accessToken);
            authTokenModel.setAppId(appId);
            authTokenModel.setAppSecret(appSecret);
            authTokenModel.setExpiretime(expirationTime.toDate());
            authTokenModel.setOpenId(openId);
            authTokenModel.setRefExpireTime(refreshExpirationTime.toDate());
            authTokenModel.setRefreshToken(refreshToken);

            authTokenModel.setValidFlag(PublicParamters.VALID_FLAG_Y);
            authTokenModel.setCreatedUser(Constants.CURRENT_USER);
            authTokenModel.setUpdatedUser(Constants.CURRENT_USER);

            // 存在并发问题
            authTokenService.addInfo(authTokenModel);

            return responseMessage;
        }

        String accessToken = authTokenModel.getAccessToken();
        DateTime expirationTime = new DateTime(authTokenModel.getExpiretime());
        String refreshToken = authTokenModel.getRefreshToken();

        if (authTokenModel.getRefExpireTime().before(new Date())) {

            DateTime nowTime = new DateTime();

            accessToken = UuidUtils.getUuid();

            refreshToken = UuidUtils.getUuid();

            expirationTime = nowTime.plusSeconds(Integer.parseInt(tokenExpireTime));

            DateTime refExpirationTime = nowTime.plusSeconds(Integer.parseInt(refreshTokenExpireTime));

            authTokenModel.setAccessToken(accessToken);
            authTokenModel.setExpiretime(expirationTime.toDate());
            authTokenModel.setRefreshToken(refreshToken);
            authTokenModel.setRefExpireTime(refExpirationTime.toDate());
            authTokenService.updateInfo(authTokenModel);
        }

        long expiresIn = (long)(Seconds.secondsBetween(new DateTime(), expirationTime).getSeconds());

        RespBodyData respBodyData = new RespBodyData();
        respBodyData.setAccess_token(accessToken);
        respBodyData.setRefresh_token(refreshToken);
        respBodyData.setExpires_in(expiresIn < 0 ? 0 : expiresIn);
        respBodyData.setOpen_id(authTokenModel.getOpenId());
        responseMessage.setData(respBodyData);
        responseMessage.setBaseMsg(Constants.CODE_SUCCESS, Constants.STATE_SUCCESS, Constants.MSG_SUCCESS);

		return responseMessage;
	}

	@Override
	public ResponseMessage refreshToken(String app_id, String app_secret, String refresh_token) throws Exception {

		ResponseMessage responseMessage = new ResponseMessage();

		// 获取账户信息
		AccountModel accountModel = accountService.getOneByAppid(app_id);

		// 判断账户信息
        logger.info("check account.");
        if (checkAccount(app_id, responseMessage, accountModel)) {
            return responseMessage;
        }

        // 获取token信息
		AuthTokenModel authTokenModel = authTokenService.getOneByAppIdAndAppSecret(app_id, app_secret);

		// 判断authTokenModel信息
		if (authTokenModel == null) {
			logger.info("invalid app_id or app_secret ,app_id:{},app_secret:{},refresh_token:{}", app_id, app_secret, refresh_token);
            responseMessage.setBaseMsg(Constants.CODE_40001, Constants.STATE_FAIL, Constants.MSG_40001);
			return responseMessage;
		}

		// 获取model中的refreshToken
		String refreshToken = authTokenModel.getRefreshToken();

		// 判断refreshToken与外部请求参数是否一致
		if (!refresh_token.equals(refreshToken)) {
			logger.info("error refresh_token,app_id:{},app_secret:{},refresh_token:{}", app_id, app_secret, refresh_token);
            responseMessage.setBaseMsg(Constants.CODE_40007, Constants.STATE_FAIL, Constants.MSG_40007);
			return responseMessage;
		}

		if (authTokenModel.getRefExpireTime().before(new Date())) {
			logger.info("refresh_token expired,app_id:{},refresh_token:{}", app_id, refresh_token);
            responseMessage.setBaseMsg(Constants.CODE_40005, Constants.STATE_FAIL, Constants.MSG_40005);
			return responseMessage;
		}

		String accessToken = UuidUtils.getUuid();

		DateTime expirationTime = new DateTime().plusSeconds(Integer.parseInt(tokenExpireTime));

		long expiresIn = (long)(Seconds.secondsBetween(new DateTime(), expirationTime).getSeconds());

		RespBodyData respBodyData = new RespBodyData();
		respBodyData.setAccess_token(accessToken);
		respBodyData.setRefresh_token(refreshToken);
		respBodyData.setExpires_in(expiresIn < 0 ? 0 : expiresIn);
		respBodyData.setOpen_id(authTokenModel.getOpenId());
		responseMessage.setData(respBodyData);
        responseMessage.setBaseMsg(Constants.CODE_SUCCESS, Constants.STATE_SUCCESS, Constants.MSG_SUCCESS);

		// 修改authToken信息
		authTokenModel.setAccessToken(accessToken);
		authTokenModel.setExpiretime(expirationTime.toDate());
		authTokenService.updateInfo(authTokenModel);

		return responseMessage;
	}

    @Override
	public ResponseMessage token_auth(String access_token, String openid) throws Exception {
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		AuthTokenModel model = new AuthTokenModel();
		model.setOpenId(openid);
		model.setAccessToken(access_token);
		model = authTokenService.getOneByAuthToken(model);
		
		if (model == null) {
			logger.info("invalid access_token or invalid open_id,accessToken:{},openId:{}", access_token, openid);
            responseMessage.setBaseMsg(Constants.CODE_40032, Constants.STATE_FAIL, Constants.MSG_40032);
			return responseMessage;
		}
		
		if(model.getExpiretime().before(new Date())) {
			logger.info("access_token expired:{}", access_token);
            responseMessage.setBaseMsg(Constants.CODE_40029, Constants.STATE_FAIL, Constants.MSG_40029);
			return responseMessage;
		}
	
		if(!openid.equals(model.getOpenId())) {
			logger.info("invalid openid:{}", openid);
            responseMessage.setBaseMsg(Constants.CODE_40031, Constants.STATE_FAIL, Constants.MSG_40031);
			return responseMessage;
		}

		responseMessage.setBaseMsg(Constants.CODE_SUCCESS, Constants.STATE_SUCCESS, Constants.MSG_SUCCESS);

		return responseMessage;
	}

    /**
     * 获取账号下的权限信息
     */
	@Override
	public ResponseMessage interface_list(String access_token, String openid) throws Exception {
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		AuthTokenModel model = new AuthTokenModel();
		model.setOpenId(openid);
		model.setAccessToken(access_token);
		model = authTokenService.getOneByAuthToken(model);
		
		if (model == null) {
			logger.info("invalid access_token or invalid open_id,accessToken:{},openId:{}", access_token, openid);
            responseMessage.setBaseMsg(Constants.CODE_40032, Constants.STATE_FAIL, Constants.MSG_40032);
			return responseMessage;
		}
	
		if (model.getExpiretime().before(new Date())) {
			logger.info("access_token expired:{}", access_token);
            responseMessage.setBaseMsg(Constants.CODE_40029, Constants.STATE_FAIL, Constants.MSG_40029);
			return responseMessage;
		}
		
		if(!openid.equals(model.getOpenId())) {
            logger.info("invalid openid:{}", openid);
            responseMessage.setBaseMsg(Constants.CODE_40031, Constants.STATE_FAIL, Constants.MSG_40031);
            return responseMessage;
        }
		
		//根据appId获取其下所有渠道中的默认方案对应的authMapp
		List<InterfaceInfoDto> interfaceInfoList = authMappService.selectListsByAppid(model.getAppId());
		
		RespBodyData respBodyData = new RespBodyData();
		respBodyData.setInterface_list(interfaceInfoList);
		responseMessage.setData(respBodyData);
        responseMessage.setBaseMsg(Constants.CODE_SUCCESS, Constants.STATE_SUCCESS, Constants.MSG_SUCCESS);
		
		return responseMessage;
	}

    /**
     *检查账号的有效性，无效返回 true
     */
    private boolean checkAccount(String app_id, ResponseMessage responseMessage, AccountModel accountModel) {
        if (accountModel == null || accountModel.getValidFlag().equals(PublicParamters.VALID_FLAG_N)) {
            if(accountModel == null) {
                logger.info("not exist this app_id:{}", app_id);
            } else {
                logger.info("app_id invalid:{}", app_id);
            }
            responseMessage.setBaseMsg(Constants.CODE_40001,Constants.STATE_FAIL,Constants.MSG_40001);
            return true;
        }
        return false;
    }

	@Override
	public Map<String, String> selectPubCodeByConditions(String parameterType) throws Exception {
		Map<String, String> map = new HashMap<String,String>();
		
		AuthPubCodeModel model = new AuthPubCodeModel();
		model.setValidFlag(PublicParamters.VALID_FLAG_Y);
		model.setParameterType(parameterType);
		
		List<AuthPubCodeModel> lists = authPubCodeDao.selectAllByConditions(model);
		
		for (AuthPubCodeModel model1 : lists) {
			map.put(model1.getParameterName(),model1.getParameterValue());
		}
		
		return map;
	}

}
