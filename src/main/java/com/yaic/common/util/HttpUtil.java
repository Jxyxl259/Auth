package com.yaic.common.util;

import com.yaic.servicelayer.http.HttpTransceiver;
import com.yaic.servicelayer.http.wrapper.HttpPostRawWrapper;
import com.yaic.servicelayer.http.wrapper.HttpResponseWrapper;
import org.springframework.util.StringUtils;

/**
 * @Description: http请求工具
 * @author: jiangxy
 * @date: 2018-7-27  16:15
 */
public class HttpUtil {

	public static HttpResponseWrapper connectServer(final String content, final String callbackUrl, Integer connectTimeout, Integer socketTimeout, String mimeType) {
		final HttpPostRawWrapper httpPostWrapper = new HttpPostRawWrapper();
		httpPostWrapper.setServerUrl(callbackUrl);
		httpPostWrapper.setConnectTimeout(connectTimeout == null ? 30 * 1000 : connectTimeout);
		httpPostWrapper.setSocketTimeout(socketTimeout == null ? 30 * 1000 : socketTimeout);
		httpPostWrapper.setRawBody(content);
		httpPostWrapper.setMimeType(StringUtils.isEmpty(mimeType) ? "text/plain" : mimeType);
		return HttpTransceiver.doPost(httpPostWrapper);
	}

}