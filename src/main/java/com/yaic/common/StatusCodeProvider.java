package com.yaic.common;

import com.yaic.servicelayer.app.provider.AppNoProvider;
import com.yaic.servicelayer.constant.AppSystem;
import com.yaic.servicelayer.constant.StatusCodeMap;
import com.yaic.servicelayer.http.wrapper.HttpResponseWrapper;

/**
 * 提供OPEN管理系统的应用系统编号
 *
 * @author Qu Dihuai
 *
 */
public class StatusCodeProvider {
	/**
	 * 默认的接口编号
	 */
	private final static String DEFAULT_INTERFACE_NO = "000";

	/**
	 * 开放平台(OPEN)系统
	 */
	public final static String SYSTEMNO_INTERFACE_OPEN = provideAppNo(AppSystem.Midend.OPEN) + DEFAULT_INTERFACE_NO;

	/**
	 * 提供当前系统的系统编号
	 */
	public static String provideAppNo(String appNo) {
		return AppNoProvider.provide(appNo);
	}

	/**
	 * 如果数据收发器(HttpTransceiver)的statusCode值为0, 返回默认的code值
	 */
	public static String getCode(final HttpResponseWrapper result, final String defaultCode)
	{
		if (result.getStatusCode() == 0)
		{
			return defaultCode;
		}
		return StatusCodeMap.fromStatus(result.getStatusCode());
	}
}