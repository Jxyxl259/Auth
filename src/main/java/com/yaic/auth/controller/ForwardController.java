package com.yaic.auth.controller;

import com.alibaba.fastjson.JSON;
import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.service.OAuthService;
import com.yaic.auth.thirdparty.model.*;
import com.yaic.auth.thirdparty.service.*;
import com.yaic.common.Constants;
import com.yaic.common.ResponseMessage;
import com.yaic.common.util.RSACoder;
import com.yaic.servicelayer.charset.StandardCharset;
import com.yaic.servicelayer.codec.digest.MD5Helper;
import com.yaic.servicelayer.http.HttpTransceiver;
import com.yaic.servicelayer.http.wrapper.HttpPostRawWrapper;
import com.yaic.servicelayer.http.wrapper.HttpResponseWrapper;
import com.yaic.servicelayer.util.CollectionUtil;
import com.yaic.servicelayer.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 服务转发功能
 */
@RestController
public class ForwardController {

	private static final Logger logger = LoggerFactory.getLogger(ForwardController.class);

	private final  ServerService serverService;

    private final  AuthMappingService authMappingService;

    private final  AuthEncryptService authEncryptService;

    private final  AccountService accountService;

    private final  AuthTokenService authTokenService;
	
    private final OAuthService oAuthService;

    @Autowired
    public ForwardController(ServerService serverService, AuthMappingService authMappingService, AuthEncryptService authEncryptService,
                                AccountService accountService, AuthTokenService authTokenService, OAuthService oAuthService) {
        this.serverService = serverService;
        this.authMappingService = authMappingService;
        this.authEncryptService = authEncryptService;
        this.accountService = accountService;
        this.authTokenService = authTokenService;
        this.oAuthService = oAuthService;
    }

    @PostMapping("/sns/**")
	public void FilterControl(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String[] urls = this.generateAllPattenURL(request);
		
        logger.info("Method:{};CharacterEncoding:{};ContentType:{}",request.getMethod(), request.getCharacterEncoding(), request.getContentType());
		
		String urlPart = urls[0];

		String accept = request.getHeader("Accept"); // application/json || application/xml

		String dataSource = request.getParameter("data_source");
		String projectCode = request.getParameter("agrt_code");

		// 1、渠道代码和方案代码都为空--兼容原系统
		if (StringUtil.isEmpty(dataSource) && StringUtil.isEmpty(projectCode)) {

			logger.info("both dataSource and projectCode are empty.");
			AuthMappingModel authMapping = new AuthMappingModel();
			authMapping.setRequestUrl(urlPart);
			authMapping.setValidFlag(PublicParamters.VALID_FLAG_Y);
			List<AuthMappingModel> authMappingList = authMappingService.getList(authMapping);
			if (CollectionUtil.isEmpty(authMappingList)) {
				logger.info("authMapping is null.");
				this.formateErrorMsg(accept, Constants.CODE_50001, Constants.MSG_50001, response);
				return;
			}
			
			// 1.1、确定接口类型是否包含"自定义"--原不加密或白名单情况
			authMapping = this.getAuthMappingType(authMappingList, PublicParamters.REQUEST_TYPE_HISTORY_CUSTOM);
			if (authMapping != null) {
				logger.info("request type is {}.", PublicParamters.REQUEST_TYPE_HISTORY_CUSTOM);
				this.customProcess(request, response, authMapping.getServerId(), accept);
				return;
			}

            AuthEncryptModel oneByAuthId = null;
			// 1.2、确定接口类型是否包含"原始"--原通用鉴权情况
			authMapping = this.getAuthMappingType(authMappingList, PublicParamters.REQUEST_TYPE_HISTORY_COMMON);
			if (authMapping != null) {
				logger.info("request type is {}.", PublicParamters.REQUEST_TYPE_HISTORY_COMMON);
				if(authMapping.getAuthId() != null) {
                    oneByAuthId = authEncryptService.getOneByAuthId(authMapping.getAuthId());
                }
				this.primevalProcess(request, response, authMapping.getServerId(), oneByAuthId, urls, accept, true);
				return;
			}
			
			// 1.3、确定接口类型是否包含"加密" -- 原验签、报文加密情况
			authMapping = this.getAuthMappingType(authMappingList, PublicParamters.REQUEST_TYPE_HISTORY_ENCRYPT);
			if (authMapping != null) {
				logger.info("request type is {}.", PublicParamters.REQUEST_TYPE_HISTORY_ENCRYPT);
                if(authMapping.getAuthId() != null) {
                    oneByAuthId = authEncryptService.getOneByAuthId(authMapping.getAuthId());
                }
				this.encryptProcess(request, response, authMapping.getServerId(), oneByAuthId, urls, accept, true);
				return;
			}

			logger.info("request type error.");
			// 1.4、没有接口权限结束流程
			this.formateErrorMsg(accept, Constants.CODE_50007, Constants.MSG_50007, response);
			return;
		}

		//新流程,data_source和project_code不全为空

		logger.info("dataSource and projectCode are not all empty.");

		// 2、渠道代码和方案代码不为空
		Map<String, String> paramMap = new HashMap<>();
		final String appId = request.getParameter("app_id");
		paramMap.put("appId", appId);
		paramMap.put("requestUrl", urlPart);
		paramMap.put("dataSource", dataSource);
		paramMap.put("projectCode", projectCode);

		logger.info("data_source:{},project_code:{},app_id:{},requestUrl:{}.", dataSource, projectCode, appId, urlPart);


		List<Map<String, Object>> authMappingList = authMappingService.selectByProjectDataSource(paramMap);

		// 为了兼容旧数据，渠道方本应不传也传了渠道代码，方案代码情况
		if(CollectionUtil.isEmpty(authMappingList)) {
		    logger.info("selectByProjectDataSourceOriginal.");
			authMappingList = authMappingService.selectByProjectDataSourceOriginal(paramMap);
		}
		
		if (CollectionUtil.isEmpty(authMappingList)) {
			logger.info("auth mapping is empty.");
			this.formateErrorMsg(accept, Constants.CODE_50001, Constants.MSG_50001, response);
			return;
		} else if (authMappingList.size() > 2) {
			logger.info("auth mapping more than one.");
			this.formateErrorMsg(accept, Constants.CODE_50008, Constants.MSG_50008, response);
			return;
		} else if (authMappingList.size() == 2) {
		    String requestType1 = (String)authMappingList.get(0).get("requestType");
            String requestType2 = (String)authMappingList.get(1).get("requestType");
            if(requestType1.equals(requestType2)) {
                logger.info("auth mapping more than one.");
                this.formateErrorMsg(accept, Constants.CODE_50008, Constants.MSG_50008, response);
                return;
            } else {
                if(!PublicParamters.REQUEST_TYPE_PROJECT.equals(requestType1)){
                    authMappingList.remove(0);
                } else if (!PublicParamters.REQUEST_TYPE_PROJECT.equals(requestType2)) {
                    authMappingList.remove(1);
                }
            }
        }
        if (CollectionUtil.isEmpty(authMappingList)) {
            logger.info("auth mapping is empty.");
            this.formateErrorMsg(accept, Constants.CODE_50001, Constants.MSG_50001, response);
            return;
        }

        final Map<String, Object> authMapping = authMappingList.get(0);
        final Integer serverId = ((Long) (authMapping.get("serverId"))).intValue();
        final String requestType = (String)authMapping.get("requestType");
        final Integer authId = authMapping.get("authId") == null ? 0 : Integer.parseInt(authMapping.get("authId").toString());

        // 2.1、"方案"走5
        // 2.2、"渠道"走6
		if (PublicParamters.REQUEST_TYPE_PROJECT.equals(requestType) || PublicParamters.REQUEST_TYPE_DATA_SOURCE.equals(requestType)) {

			logger.info("request type is {}.", requestType);
            AuthEncryptModel authEncrypt;
            authEncrypt = authEncryptService.getOneByAuthId(authId);

            if (authEncrypt == null) {
                logger.info("can not find encrypt info,authId:{}",authId);
                this.formateErrorMsg(accept, Constants.CODE_50009, Constants.MSG_50009, response);
                return;
            }

            if(PublicParamters.AUTH_TYPE_COMMON_AUTH.equals(authEncrypt.getAuthType())) { // 特殊情况通用鉴权单独走出来,其他走另外一个分支
				logger.info(PublicParamters.AUTH_TYPE_COMMON_AUTH);
				this.primevalProcess(request, response, serverId, authEncrypt, urls, accept, false);
			} else {
                logger.info(authEncrypt.getAuthType());
				this.encryptProcess(request, response, serverId, authEncrypt, urls, accept, false);
			}
			return;
		}

		// 2.3、"自定义"--新流程兼容旧数据
		if (PublicParamters.REQUEST_TYPE_HISTORY_CUSTOM.equals(requestType)) {
			logger.info("request type is {}.", PublicParamters.REQUEST_TYPE_HISTORY_CUSTOM);
			this.customProcess(request, response, serverId, accept);
			return;
		}

        AuthEncryptModel authEncrypt = null;
		if(authId != null) {
            authEncrypt = authEncryptService.getOneByAuthId(authId);
        }

        // 2.4、"原始"--新流程兼容旧数据
        if (PublicParamters.REQUEST_TYPE_HISTORY_COMMON.equals(requestType)) {
            logger.info("request type is {}.", PublicParamters.REQUEST_TYPE_HISTORY_COMMON);
            this.primevalProcess(request, response, serverId, authEncrypt, urls, accept, true);
            return;
        }
		
		// 2.5、确定接口类型是否包含"加密"(兼容旧渠道接口加密情况),有则走验签、解密、加密处理过程--新流程兼容旧数据
		if (PublicParamters.REQUEST_TYPE_HISTORY_ENCRYPT.equals(requestType)) {
			logger.info("request type is {}.", PublicParamters.REQUEST_TYPE_HISTORY_CUSTOM);
			this.encryptProcess(request, response, serverId, authEncrypt, urls, accept, true);
		}
	}
	
	/**
	 * 新通用流程:验签、解密、加密处理过程
	 */
	private void encryptProcess(HttpServletRequest request, HttpServletResponse response, Integer serverId, AuthEncryptModel authEncrypt, String[] urls, String accept, boolean isHistory) throws Exception {

		String appId = request.getParameter("app_id");
        String sign = request.getParameter("sign");
        StringBuilder checkMsg = new StringBuilder();
        if (StringUtil.isEmpty(appId)) {
            checkMsg.append("app_id,");
        }
        //当鉴权类型为 MD5和RSA时,需判断sign非空
        if(StringUtil.isEmpty(sign) && (PublicParamters.AUTH_TYPE_MD5_1.equals(authEncrypt.getAuthType().toUpperCase()) 
        		|| PublicParamters.AUTH_TYPE_RSA_1.equals(authEncrypt.getAuthType().toUpperCase()))) {
            checkMsg.append("sign,");
        }
        if (StringUtil.isNotEmpty(checkMsg.toString())) {
            logger.info("Invalid param,requestUrl:{},accept:{},errorCode:{}", urls[0], accept, Constants.CODE_40006);
            this.formateErrorMsgForEncrypt(accept, Constants.CODE_40006, checkMsg.substring(0, checkMsg.length() - 1) + " can not be empty", response,authEncrypt);
            return;
        }

        if(isHistory) {
            if(historyAuth(appId, serverId)) {
                logger.error("no permission access this resource,appId:{},sign:{},errorCode:{}", appId, sign, Constants.CODE_50002);
                this.formateErrorMsg(accept, Constants.CODE_50002, Constants.MSG_50002, response);
                return;
            }
        }

        String content = this.getContentStr(request, StandardCharset.UTF_8.name());
        logger.info("request param: appId:{},requestUrl:{},accept:{},sign:{},content:{}", appId ,urls[0], accept,sign, content);

        // 鉴权
        String ipAddress = this.getIpAddress(request);
        if (!this.checkAuth(authEncrypt, content, sign, ipAddress)) {
            logger.info("Invalid param,requestUrl:{},accept:{},errorCode:{}", urls[0], accept, Constants.MSG_40008);
            this.formateErrorMsgForEncrypt(accept, Constants.CODE_40008, Constants.MSG_40008, response,authEncrypt);
            return;
        } 
        //验签通过之后进行解密密文操作，md5的内容是明文，不需要解密。 rsa的签名是对密文签名，成功之后解密。 调换位置之后，对于MD5的方式即选择签名又做加密操作，这种类型的支持，只能先加密之后对密文签名，同rsa的操作流程
        if (authEncrypt != null) {
            // 解密内容
            content = this.decrypt(response, content, authEncrypt, urls[0], accept);
            if(content == null) {
                return;
            }
        }

        // 获取服务器信息
        ServerModel server = serverService.getOneByServerId(serverId);
		if (server == null) {
			this.formateErrorMsg(accept, Constants.CODE_50006, Constants.MSG_50006, response);
			return;
		}

		//服务状态判断
        Map<String,String> serverStatusMap = oAuthService.selectPubCodeByConditions("SERVER_STATUS");
        if ((!server.getServerStatus().equals(serverStatusMap.get("SERVER_STATUS_1"))
                && !server.getServerStatus().equals(serverStatusMap.get("SERVER_STATUS_9")))) {
            logger.info("server status invalid,status:{},serverType:{},env:{},serverUrl:{}",server.getServerStatus(),server.getServerType(),
                    server.getServerEnv(),server.getServerUrl());
            this.formateErrorMsg(accept, Constants.CODE_50011, Constants.MSG_50011 + "[" + server.getServerStatus() + "]", response);
            return;
        }
        
    	// 转发内容
        String result = "";
        try {
            String transUrl = server.getServerUrl() + "?appId=" + appId;

            HttpPostRawWrapper httpPostWrapper = new HttpPostRawWrapper();
            httpPostWrapper.setServerUrl(transUrl);
            httpPostWrapper.setRawBody(content);
            httpPostWrapper.setMimeType("text/plain");
            httpPostWrapper.setCharset(StandardCharset.UTF_8.name());
            HttpResponseWrapper httpResponseWrapper = HttpTransceiver.doPost(httpPostWrapper);
            if (httpResponseWrapper.getStatus()) {
            	result = (String) httpResponseWrapper.getContent();
            	// 加密处理
                if(result != null) {
                    if (authEncrypt != null) {
                        result = this.encrypt(response, result, authEncrypt, urls[0], accept);
                        if (result == null) {
                            return;
                        }
                    }
                }
                response.setCharacterEncoding(StandardCharset.UTF_8.name());
                response.setContentType("text/html; charset=" + StandardCharset.UTF_8.name());
                response.getWriter().write(result == null ? "" : result);
                response.getWriter().flush();
            } else {
                logger.info("ErrorMessage:{}", httpResponseWrapper.getErrorMessage());
                this.formateErrorMsgForEncrypt(accept, Constants.CODE_FAIL, httpResponseWrapper.getErrorMessage(), response,authEncrypt);
            }
        } catch (IllegalStateException e) {
            try {
                result = "The request is not responding. Please try again later, error:" + e;
                logger.error("The request is not responding. Please try again later, error:{}", e);
                this.formateErrorMsgForEncrypt(accept, Constants.CODE_FAIL, "The request is not responding. Please try again later", response,authEncrypt);
            } catch (Exception ex) {
                throw new IOException(ex);
            }
        } catch (Exception e) {
            try {
                result = "Request platform connection error, please contact the relevant personnel, error:" + e;
                logger.error("Request platform connection error, please contact the relevant personnel, error:{}", e);
                this.formateErrorMsgForEncrypt(accept, Constants.CODE_FAIL, "The request is not responding. Please try again later", response,authEncrypt);
            } catch (Exception ex) {
                throw new IOException(ex);
            }
        } finally {
            logger.info("Return Interface info:{}", result);
        }
		
	}

    private boolean historyAuth(String appId, Integer serverId) throws IOException {
        // 历史数据兼容
        List<AuthMappingModel> mappingList = authMappingService.getMappingByAppId(appId);
        for(AuthMappingModel dto : mappingList) {
            if(!serverId.equals(dto.getServerId())) {
                return true;
            }
        }
        return false;
    }

    /**
	 * 特殊处理:通用鉴权
	 */
	private void primevalProcess(HttpServletRequest request, HttpServletResponse response, Integer serverId,
			AuthEncryptModel authEncrypt, String[] urls, String accept, boolean isHistory) throws Exception {

        if (accept != null && !"*/*".equals(accept) && !"application/json".equals(accept) && !"application/xml".equals(accept)) {
            throw new RuntimeException("not support this accept");
        }

        String accessToken = request.getParameter("access_token");
		String openId = request.getParameter("open_id");

		StringBuilder checkMsg = new StringBuilder();
		if (StringUtil.isEmpty(accessToken)) {
			checkMsg.append("access_token,");
		}
		if (StringUtil.isEmpty(openId)) {
			checkMsg.append("open_id,");
		}
		if (StringUtil.isNotEmpty(checkMsg.toString())) {
			logger.info("Invalid param,requestUrl:{},accept:{},errorCode:{}", urls[0], accept, Constants.CODE_40006);
			this.formateErrorMsg(accept, Constants.CODE_40006, checkMsg.substring(0, checkMsg.length() - 1) + " can not be empty", response);
			return;
		}

		AuthTokenModel authToken = new AuthTokenModel();
		authToken.setOpenId(openId);
		authToken.setAccessToken(accessToken);
		authToken = authTokenService.getOneByAuthToken(authToken);
		if (authToken == null) {
			logger.info("invalid access_token or invalid open_id,accessToken:{},openId:{},accept:{}", accessToken, openId, accept);
			this.formateErrorMsg(accept, Constants.CODE_40032, Constants.MSG_40032, response);
			return;
		}
		if (!openId.equals(authToken.getOpenId())) {
			logger.info("invalid openId,accessToken:{},openId:{},accept:{},errorCode:{}", accessToken, openId, accept,
					Constants.CODE_40031);
			this.formateErrorMsg(accept, Constants.CODE_40031, Constants.MSG_40031, response);
			return;
		}

		Date now = new Date();
		if (authToken.getExpiretime().before(now)) {
			if (authToken.getRefExpireTime().before(now)) {
				logger.info("refresh_token expired,accessToken:{},refresh_token:{},openId:{},accept:{},errorCode:{}", accessToken, authToken.getRefreshToken(), openId, accept, Constants.CODE_40005);
				this.formateErrorMsg(accept, Constants.CODE_40005, Constants.MSG_40005, response);
				return;
			} else {
				logger.info("access_token expired,accessToken:{},openId:{},accept:{},errorCode:{}", accessToken, openId, accept, Constants.CODE_40029);
				this.formateErrorMsg(accept, Constants.CODE_40029, Constants.MSG_40029, response);
				return;
			}
		}

		AccountModel account = accountService.getOneByAppid(authToken.getAppId());
		if (account == null || account.getValidFlag() != 1) {
			logger.info("not find user form access_token,accessToken:{},openId:{},accept:{},errorCode:{}", accessToken, openId, accept, Constants.CODE_40030);
			this.formateErrorMsg(accept, Constants.CODE_40030, Constants.MSG_40030, response);
			return;
		}

		// 历史数据兼容
        if(isHistory) {
            if(!historyAuth(authToken.getAppId(), serverId)) {
                logger.error("no permission access this resource,appId:{},accessToken:{},openId:{},errorCode:{}", authToken.getAppId(), accessToken, openId, Constants.CODE_50002);
                this.formateErrorMsg(accept, Constants.CODE_50002, Constants.MSG_50002, response);
                return;
            }
        }

        request.setCharacterEncoding(StandardCharset.UTF_8.name());
		String content = this.getContentStr(request, StandardCharset.UTF_8.name());
		logger.info("Receive IP:{}  openId:{}  Interface_info:{}", this.getIpAddress(request), openId, content);

        // 获取加密信息
        if (authEncrypt != null) {
            // 解密内容
            content = this.decrypt(response, content, authEncrypt, urls[0], accept);
        }

        // 获取服务信息
        ServerModel server = serverService.getOneByServerId(serverId);
		if (server == null) {
			this.formateErrorMsg(accept, Constants.CODE_50006, Constants.MSG_50006, response);
			return;
		}
        Map<String,String> serverStatusMap = oAuthService.selectPubCodeByConditions("SERVER_STATUS");
        if ((!server.getServerStatus().equals(serverStatusMap.get("SERVER_STATUS_1"))
                && !server.getServerStatus().equals(serverStatusMap.get("SERVER_STATUS_9")))) {
            logger.info("server status invalid,status:{},serverType:{},env:{},serverUrl:{}",server.getServerStatus(),server.getServerType(),
                    server.getServerEnv(),server.getServerUrl());
            this.formateErrorMsg(accept, Constants.CODE_50011, Constants.MSG_50011 + "[" + server.getServerStatus() + "]", response);
            return;
        }
        
		String result = "";
		try {
			String transUrl = server.getServerUrl() + "?appId=" + authToken.getAppId();

			HttpPostRawWrapper httpPostWrapper = new HttpPostRawWrapper();
			httpPostWrapper.setServerUrl(transUrl);
			httpPostWrapper.setMimeType("text/plain");
			httpPostWrapper.setRawBody(content);
			httpPostWrapper.setCharset(StandardCharset.UTF_8.name());
			HttpResponseWrapper httpResponseWrapper = HttpTransceiver.doPost(httpPostWrapper);
			if (httpResponseWrapper.getStatus()) {
				result = (String) httpResponseWrapper.getContent();
			} else {
				logger.info("ErrorMessage:{}", httpResponseWrapper.getErrorMessage());
				this.formateErrorMsg(accept, Constants.CODE_FAIL, httpResponseWrapper.getErrorMessage(), response);
				return;
			}
		} catch (IllegalStateException e) {
			try {
				result = "The request is not responding. Please try again later, error:" + e;
				logger.error("The request is not responding. Please try again later, error:{}", e);
				this.formateErrorMsg(accept, Constants.CODE_FAIL, "The request is not responding. Please try again later", response);
				return;
			} catch (Exception ex) {
				throw new IOException(ex);
			}
		} catch (Exception e) {
			try {
				result = "Request platform connection error, please contact the relevant personnel, error:" + e;
				logger.error("Request platform connection error, please contact the relevant personnel, error:{}", e);
				this.formateErrorMsg(accept, Constants.CODE_FAIL, "The request is not responding. Please try again later", response);
				return;
			} catch (Exception ex) {
				throw new IOException(ex);
			}
		} finally {
			logger.info("Return Interface info:{}", result);
		}

		// 加密内容
        if(authEncrypt != null) {
            result = this.encrypt(response, result, authEncrypt, urls[0], accept);
            if(result == null) {
                return;
            }
        }

		response.setCharacterEncoding(StandardCharset.UTF_8.name());
		response.setContentType("text/html; charset=" + StandardCharset.UTF_8.name());
		response.getWriter().write(result);
		response.getWriter().flush();
	}

	/**
	 * 特殊处理:自定义处理过程
	 */
	private void customProcess(HttpServletRequest request, HttpServletResponse response, Integer serverId, String accept) throws Exception {
		String result = null;
		
		Map<String,String> serverStatusMap = oAuthService.selectPubCodeByConditions("SERVER_STATUS");
		
		// 获取服务信息
		ServerModel server = serverService.getOneByServerId(serverId);
		if (server == null || (!server.getServerStatus().equals(serverStatusMap.get("SERVER_STATUS_1")) 
				&& !server.getServerStatus().equals(serverStatusMap.get("SERVER_STATUS_9")))) {
			this.formateErrorMsg(accept, Constants.CODE_50006, Constants.MSG_50006, response);
			return;
		}
		
		try {
			StringBuilder transUrl = new StringBuilder(server.getServerUrl());
			String transUrlNew = transUrl.toString();
			Enumeration<String> paramNameEnum = request.getParameterNames();
			if (paramNameEnum.hasMoreElements()) {
				transUrl.append("?");
				while (paramNameEnum.hasMoreElements()) {
					String paramName = (String) paramNameEnum.nextElement();
					String paramValue = request.getParameter(paramName);
					logger.info(paramName + "=" + paramValue);
					transUrl.append(paramName).append("=").append(paramValue).append("&");
				}
				transUrlNew = transUrl.substring(0, transUrl.length() - 1);
			}
			
			String content = this.getContentStr(request, StandardCharset.UTF_8.name());
			logger.info("Receive IP:{} Interface info:{}", this.getIpAddress(request), content);

            HttpPostRawWrapper httpPostWrapper = new HttpPostRawWrapper();
			httpPostWrapper.setServerUrl(transUrlNew);
			httpPostWrapper.setMimeType("text/plain");
			httpPostWrapper.setRawBody(content);
			httpPostWrapper.setCharset(StandardCharset.UTF_8.name());
			HttpResponseWrapper httpResponseWrapper = HttpTransceiver.doPost(httpPostWrapper);
			if (httpResponseWrapper.getStatus()) {
				result = (String) httpResponseWrapper.getContent();
			} else {
				result = "system exception";
				logger.info("ErrorMessage:{}", httpResponseWrapper.getErrorMessage());
				this.formateErrorMsg(accept, Constants.CODE_FAIL,httpResponseWrapper.getErrorMessage(), response);
				return;
			}

			response.setCharacterEncoding(StandardCharset.UTF_8.name());
			response.setContentType(accept + "; charset=" + StandardCharset.UTF_8.name());
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (Exception e) {
			result = "Request connection error:" + e;
			logger.error("Request connection error:{}", e);
			throw new IOException(e);
		} finally {
			logger.info("Return Interface info:{}", result);
		}
	}

    /**
     * 加密响应信息
     */
    private String encrypt(HttpServletResponse response, String result, AuthEncryptModel authEncrypt, String url, String accept) throws IOException {
        try {
            if (PublicParamters.ENCRYPT_TYPE_AES_1.equals(authEncrypt.getEncryptType())) {       // AES_1
                // 待实现
                logger.warn("Invalid param,requestUrl:{},accept:{},errorCode:{}", url, accept, Constants.MSG_40010);
                this.formateErrorMsg(accept, Constants.CODE_40010, Constants.MSG_40010, response);
                return null;
            } else if (PublicParamters.ENCRYPT_TYPE_RSA_1.equals(authEncrypt.getEncryptType())) {   // RSA Param1:对方公开密钥[publickey]
                result = RSACoder.encryptByPublicKey(result, authEncrypt.getEncryptParam1());
            }
        } catch (Exception e) {
            logger.warn("Invalid param,requestUrl:{},accept:{},errorCode:{}", url, accept, Constants.MSG_40011);
            this.formateErrorMsg(accept, Constants.CODE_40011, Constants.MSG_40011, response);
            return null;
        }
        return result;
    }

    /**
     * 解密请求信息
     */
    private String decrypt(HttpServletResponse response, String content, AuthEncryptModel authEncrypt, String url,
                           String accept) throws IOException {
        try {
            if (PublicParamters.ENCRYPT_TYPE_AES_1.equals(authEncrypt.getEncryptType())) {        // AES_1
                // 待实现
                throw new RuntimeException("AES_1解密算法还未实现");
            } else if (PublicParamters.ENCRYPT_TYPE_RSA_1.equals(authEncrypt.getEncryptType())) { // RSA_1 双方各自持有一对密钥,其中Param1:对方公开密钥[publickey],Param2:我方私有密钥[privatekey]
                content = RSACoder.decryptByPublicKey(content, authEncrypt.getEncryptParam1());
                logger.info("decryptByPublicKey:{}", content);
                return content;
            }
        } catch (Exception e) {
            logger.warn("Invalid param,requestUrl:{},accept:{},errorCode:{}", url, accept, Constants.MSG_40009);
            this.formateErrorMsgForEncrypt(accept, Constants.CODE_40009, Constants.MSG_40009, response, authEncrypt);
            return null;
        }
        return content;
    }

    /**
     * 鉴权
     */
    private boolean checkAuth(AuthEncryptModel authEncrypt, String content, String sign, String ipAddress) {
        try {
            if (PublicParamters.AUTH_TYPE_NO_AUTH.equals(authEncrypt.getAuthType().toUpperCase())) {
                return true;
            } else if (PublicParamters.AUTH_TYPE_MD5_1.equals(authEncrypt.getAuthType().toUpperCase())) { // MD5
                return sign.equals(MD5Helper.sign(authEncrypt.getAuthParam1().trim() + content, StandardCharset.UTF_8.name()));
            } else if (PublicParamters.AUTH_TYPE_RSA_1.equals(authEncrypt.getAuthType().toUpperCase())) { // 非对称RSA加密
                return RSACoder.verify(content, authEncrypt.getAuthParam1().trim(), sign);
            } else if (PublicParamters.AUTH_TYPE_IP_1.equals(authEncrypt.getAuthType().toUpperCase())) {  // 由于部署服务前面有负载服务器不能获取到对方IP
                if(StringUtil.isNotEmpty(authEncrypt.getAuthParam1().trim())) {
                    if(this.validateIP(ipAddress, authEncrypt.getAuthParam1()) || Constants.PUBLIC_ALLOW_IP.equals(authEncrypt.getAuthParam1())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("verify sign error:{}", e);
            return false;
        }
        return false;
    }

    /**
	 * 获取请求Mapping类型
	 */
	private AuthMappingModel getAuthMappingType(List<AuthMappingModel> authMappingList, String type) {
		AuthMappingModel authMapping = null;
		for (AuthMappingModel item : authMappingList) {
			if (type.equals(item.getRequestType())) {
				authMapping = item;
				break;
			}
		}
		return authMapping;
	}

	/**
	 * 获取请求内容
	 */
	private String getContentStr(HttpServletRequest request, String encodingType) throws IOException {
		List<String> ioList = IOUtils.readLines(request.getInputStream(), encodingType);
		StringBuilder sb = new StringBuilder(1024);
		for (String str : ioList) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	/**
	 * 获取请求路径
	 */
	private String[] generateAllPattenURL(HttpServletRequest request) {
		String[] url = new String[2];

		StringBuilder reqPath = new StringBuilder();
		reqPath.append("");

		String servletPath = request.getServletPath();
		/* 添加 去掉URL中/SNS字符串 */
		if (servletPath != null && servletPath.trim().length() > 0 && servletPath.contains("/sns/")) {
			reqPath.append(servletPath.replace("/sns/", ""));
		}
		String pathInfo = request.getPathInfo();
		if (pathInfo != null && pathInfo.trim().length() > 0) {
			reqPath.append(pathInfo);
		}
		url[0] = reqPath.toString();

		String queryString = request.getQueryString();
		if (queryString != null && queryString.trim().length() > 0) {
			reqPath.append("?");
			reqPath.append(queryString);
		}
		url[1] = reqPath.toString();

		return url;
	}

	/**
	 * 获取用户真实IP地址
	 */
	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

    /**
     * 校验所给IP是否满足给定规则
     *
     * @param ipStr 待校验IP
     * @param ipPattern IP匹配规则,支持 * 匹配所有和 - 匹配范围,用分号分隔 ; 例如：192.168.1.*;192.168.2.1-128
     */
    private boolean validateIP(String ipStr, String ipPattern) {
        if (ipStr == null || ipPattern == null) {
            return false;
        }
        String[] patternList = ipPattern.split(";");
        for (String pattern : patternList) {
            if (passValidate(ipStr, pattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean passValidate(String ipStr, String pattern) {
        String[] ipStrArr = ipStr.split("\\.");
        String[] patternArr = pattern.split("\\.");
        if (ipStrArr.length != 4 || patternArr.length != 4) {
            return false;
        }
        int end = ipStrArr.length;
        if (patternArr[3].contains("-")) {
            end = 3;
            String[] rangeArr = patternArr[3].split("-");
            int from = Integer.parseInt(rangeArr[0]);
            int to = Integer.parseInt(rangeArr[1]);
            int value = Integer.parseInt(ipStrArr[3]);
            if (value < from || value > to) {
                return false;
            }
        }
        for (int i = 0; i < end; i++) {
            if (patternArr[i].equals("*")) {
                continue;
            }
            if (!patternArr[i].equalsIgnoreCase(ipStrArr[i])) {
                return false;
            }
        }
        return true;
    }


    /**
	 * 输出错误信息
	 */
	private void formateErrorMsg(String accept, String errorCode, String msg, HttpServletResponse response) throws IOException {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setState(Constants.STATE_FAIL);
		responseMessage.setCode(errorCode);
		responseMessage.setMessage(msg);
		if (accept == null || "*/*".equals(accept) || "application/json".equals(accept)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(responseMessage));
			response.getWriter().flush();
		} else if ("application/xml".equals(accept)) {
			XMLSerializer xmlSerializer = new XMLSerializer();
			xmlSerializer.setRootName("response-message");
			xmlSerializer.setTypeHintsEnabled(false);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setJsonPropertyFilter(new SelfPropertyFilter());
			response.getWriter().write(xmlSerializer.write(JSONObject.fromObject(responseMessage, jsonConfig)));
			response.getWriter().flush();
		} else if("text/html".equals(accept)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(msg==null?"system exception":msg);
			response.getWriter().flush();
		} else {
			throw new RuntimeException("not support this accept.");
		}
	}

	/**
     * 输出加密后错误信息
     */
    private void formateErrorMsgForEncrypt(String accept, String errorCode, String msg, HttpServletResponse response,AuthEncryptModel authEncrypt) throws IOException {
    	String returnTxt;
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setState(Constants.STATE_FAIL);
        responseMessage.setCode(errorCode);
        responseMessage.setMessage(msg);
        if (accept == null || "*/*".equals(accept) || "application/json".equals(accept)) {
        	returnTxt = JSON.toJSONString(responseMessage);
        } else if ("application/xml".equals(accept)) {
            XMLSerializer xmlSerializer = new XMLSerializer();
            xmlSerializer.setRootName("response-message");
            xmlSerializer.setTypeHintsEnabled(false);
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setJsonPropertyFilter(new SelfPropertyFilter());
            returnTxt = xmlSerializer.write(JSONObject.fromObject(responseMessage, jsonConfig));
        } else if("text/html".equals(accept)) {
        	returnTxt = msg==null?"system exception":msg;
        } else {
            throw new RuntimeException("not support this accept.");
        }
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
        	logger.info("return interface_info:{}",returnTxt);
        	returnTxt = this.encrypt(response, returnTxt, authEncrypt, null, accept);
        	logger.info("return encryptByPublicKey:{}",returnTxt);
			response.getWriter().write(returnTxt == null? "" : returnTxt );
		} catch (Exception e) {
			logger.error("rsa_publicKey encrypt, error:{}", e);
			response.getWriter().write("");
			e.printStackTrace();
		}
        response.getWriter().flush();
    }

    private class SelfPropertyFilter implements PropertyFilter {

        @Override
        public boolean apply(Object source, String name, Object value) {
            return value == null || (value instanceof JSONArray && ((JSONArray) value).size() == 0);
        }

    }
}