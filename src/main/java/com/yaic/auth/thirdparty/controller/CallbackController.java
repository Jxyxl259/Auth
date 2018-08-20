package com.yaic.auth.thirdparty.controller;

import com.yaic.auth.thirdparty.service.CallbackService;
import com.yaic.servicelayer.charset.StandardCharset;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.List;



/**
 * @Description: 支付成功后回调第三方（渠道）
 * @author: jiangxy
 * @date: 2018-7-25 10:47
 */
@Controller
@RequestMapping("/callback")
public class CallbackController {

	private static final Logger logger = LoggerFactory.getLogger(CallbackController.class);

	@Autowired
	private CallbackService callbackService;

	/**
	 * 该方法在支付成功后被 outerpay调用，
	 * 请求体内容包含回调第三方（渠道）所需的必要信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@RequestMapping(value = "/dealBiz")
	protected void dealBiz(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StopWatch watch = new StopWatch();
		watch.start("服务调用");

		String primitiveRequestContent = inputStream2String(request);
		if(primitiveRequestContent == null){
			logger.error("原始接口信息为空");
			return;
		}
		logger.info("接到原始接口信息：{}", primitiveRequestContent);
		String decodedRequestContent = URLDecoder.decode(primitiveRequestContent, StandardCharset.UTF_8.name());
		logger.info("解码原始接口信息：{}", decodedRequestContent);

		String result = callbackService.dealBiz(decodedRequestContent);

		disposeResponse(response, result);

		watch.stop();
		logger.info(watch.getLastTaskName() + "耗时" + watch.getTotalTimeMillis()/1000.0D + "秒");
	}




	/**
	 * 输入流转换为字符串
	 * @param request
	 * @return
	 */
	private String inputStream2String(HttpServletRequest request) {

		StringWriter sw = null;

		try {
			List<String> strings = IOUtils.readLines(request.getInputStream(), StandardCharset.UTF_8.name());

			sw = new StringWriter();

			for(String line : strings){
				sw.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("原始接口信息读取失败：{}", e.getCause());
		}

		return (sw == null || StringUtils.isEmpty(sw.toString())) ? null : sw.toString();

	}


	/**
	 * 处理请求响应
	 * @param response
	 * @param content
	 * @throws IOException
	 */
	private void disposeResponse(HttpServletResponse response, String content){

		try {
			OutputStream outputStream = response.getOutputStream();
			response.setContentType("text/html; charset=UTF-8");
			response.setContentLength(content.getBytes().length);
			outputStream.write(content.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("处理请求响应失败: " + e.toString(), e);
		}
	}

}