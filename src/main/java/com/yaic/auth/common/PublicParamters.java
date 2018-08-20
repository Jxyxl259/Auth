package com.yaic.auth.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yaic.auth.common.init.dao.AuthPubCodeDao;
import com.yaic.auth.common.init.model.AuthPubCodeModel;


@Component
@Order(value = 1)
public class PublicParamters implements CommandLineRunner {

	
	public static final String algorithmName = "SHA1";
	public static final int hashIterations = 2;
	public final static String SALT = "cms" ;
	/*
	 * 特殊账户,不能删除
	 */
	public static final Integer CAN_NOT_DELETE_ACCOUNT_ID = 1;
    
    
	/*
	 * 有效标识,1有效,0无效
	 */
	public static Integer VALID_FLAG_Y;
	public static Integer VALID_FLAG_N;
	

	/*
	 * 方案默认标识,1是默认方案 0不是默认方案
	 */
	public static Integer DEFAULT_PROJECT_Y = 1;
	public static Integer DEFAULT_PROJECT_N = 0;

	/*
	 * interface 接口类型 1.渠道 2.方案 3.原始 4.自定义
	 */
	/** 1 渠道 */
	public static String REQUEST_TYPE_DATA_SOURCE;
	/** 2 方案 */
	public static String REQUEST_TYPE_PROJECT;
	/** 3 原始 */
	public static String REQUEST_TYPE_HISTORY_COMMON;
	/** 4 自定义 */
	public static String REQUEST_TYPE_HISTORY_CUSTOM;
	/** 5 加密*/
	public static String REQUEST_TYPE_HISTORY_ENCRYPT;

	/*
	 * auth 鉴权类型 1.不鉴权 2.MD5_1 3.RSA_1 4.IP_1
	 */
	/** 1 不鉴权 */
	public static String AUTH_TYPE_NO_AUTH;
	/** 2 MD5_1 */
	public static String AUTH_TYPE_MD5_1;
	/** 3 RSA_1 */
	public static String AUTH_TYPE_RSA_1;
	/** 4 IP_1 */
	public static String AUTH_TYPE_IP_1;
	/** 5 COMMON_AUTH 通用鉴权 */
	public static String AUTH_TYPE_COMMON_AUTH;

	/*
	 * Encrypt 加密类型 1.AES_1 2.RSA_1 
	 */
	/** 1 AES_1 */
	public static String ENCRYPT_TYPE_AES_1;
	/** 2 RSA_1 */
	public static String ENCRYPT_TYPE_RSA_1;
	/** 3 RSA */
//	public static String ENCRYPT_TYPE_RSA;
	/** 4  不加密 */
	public static String ENCRYPT_TYPE_NO_ENCRYPT;

	/*
	 * ServerEnv 环境类型 1.DEV 2.SIT 3.UAT 4.PROD
	 */
	/** 1 DEV */
	public static String SERVER_ENV_TYPE_DEV;
	/** 2 SIT */
	public static String SERVER_ENV_TYPE_SIT;
	/** 3 UAT */
	public static String SERVER_ENV_TYPE_UAT;
	/** 4 PROD */
	public static String SERVER_ENV_TYPE_PROD;

	/*
	 * serverType 服务类型
	 */
//	/** 1 1 */
//	public static String SERVER_TYPE_1;
//	/** 2 2 */
//	public static String SERVER_TYPE_2;
//
//	public static String SERVER_TYPE_3;
//
//	public static String SERVER_TYPE_4;

	/*
	 * serverSmallType 服务小类
	 */
//	/** 1 1-1 */
//	public static String SERVER_SMALL_TYPE_1_1;
//	/** 1 1-2 */
//	public static String SERVER_SMALL_TYPE_1_2;
//	/** 2 1 */
//	public static String SERVER_SMALL_TYPE_2_1;
//	/** 2 2 */
//	public static String SERVER_SMALL_TYPE_2_2;

	/*
	 * callback 回调类型 1.PAY 2.RETREATS
	 */
	/** 1 支付回调 */
	public static String CALL_BACK_TYPE_PAY_CALLBACK;
	/** 1 退保回调 */
	public static String CALL_BACK_TYPE_CANCEL_INSURANCE_CALLBACK;

	private static final Logger logger = LoggerFactory.getLogger(PublicParamters.class);

	@Autowired
	private AuthPubCodeDao authPubCodeDao;

	@Override
	public void run(String... args) throws Exception {
		List<AuthPubCodeModel> authPubCodeModelList = authPubCodeDao.selectAll();
		//logger.debug("查询数据库参数：{}", parametersList);
		logger.info("加载参数");
		loadParameters(authPubCodeModelList);
		logger.info("参数加载完成");
	}

	private void loadParameters(List<AuthPubCodeModel> authPubCodeModelList) {
		try {
			Class<?> cls = PublicParamters.class;
			Object obj = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			Map<String, String> parametersMap = new HashMap<String, String>();
			for (AuthPubCodeModel authPubCodeModel : authPubCodeModelList) {
				parametersMap.put(authPubCodeModel.getParameterName(), authPubCodeModel.getParameterValue());
			}
			// 给每个属性赋值
			for (Field field : fields) {
				Class<?> type = field.getType();
				if (type == String.class) {
					String name = field.getName();
					if (StringUtils.isNotBlank(parametersMap.get(name))) {
						field.set(obj, parametersMap.get(name));
					}
				} else if (type == Integer.class) {
					String name = field.getName();
					if (StringUtils.isNotBlank(parametersMap.get(name))) {
						field.set(obj, Integer.valueOf(parametersMap.get(name)));
					}
				}
			}

//			 测试属性是否赋值成功
//			 for (Field field : fields) {
//			 String name = field.getName();
//			 Object value = field.get(obj);
//			 logger.info("{}:{}", name, value);
//			 }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
