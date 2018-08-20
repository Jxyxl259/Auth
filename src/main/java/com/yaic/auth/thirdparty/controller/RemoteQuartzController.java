package com.yaic.auth.thirdparty.controller;

import com.yaic.auth.common.PublicParamters;
import com.yaic.auth.thirdparty.service.TaskCallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

import static com.yaic.common.LogFileNameEnum.QUARTZ_TASK;

/**
 * @Description: Task任务定时调用，扫库t_auth_callback_info表，重试未成功的支付回调
 * @author: jiangxy
 * @date: 2018-7-27 21:34
 */
@Controller
public class RemoteQuartzController {

	/**
	 * 将定时任务日志输出到指定的文件中
	 * 与业务日志分离，方便查看
	 */
	private Logger log = LoggerFactory.getLogger(QUARTZ_TASK.getLogFileName());

	@Autowired
	private TaskCallbackService taskCallbackService;

	@RequestMapping("/retryCallback")
	public void retryCallBackBiz(HttpServletRequest request, HttpServletResponse response){
		log.debug("定时任务接口调用开始");

        String dealType = request.getParameter("dealType");

        String callbackType = dealType2CallbackType(dealType);

        log.debug("任务处理类型为={}", callbackType);

        taskCallbackService.dealQuartzBiz(dealType);

        try {
            response.getOutputStream();
            disposeResponse(response, callbackType + "任务调用成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("定时任务接口调用结束");
	}


    /**
     * 处理类型转换为重试回调类型说明
     * t_auth_pub_code表中存有映射关系
     * @param dealType
     * @return
     */
	private String dealType2CallbackType(String dealType){

	    String callbackType = "";

        switch(dealType){
            case "01": callbackType = PublicParamters.CALL_BACK_TYPE_PAY_CALLBACK; break;
            case "11": callbackType = PublicParamters.CALL_BACK_TYPE_CANCEL_INSURANCE_CALLBACK; break;
        }

        return callbackType;
    }



    /**
     * 处理请求响应
     * @param response
     * @param content
     * @throws IOException
     */
    private void disposeResponse(HttpServletResponse response, String content) throws IOException{
        OutputStream outputStream = response.getOutputStream();
        response.setContentType("text/plain; charset=UTF-8");
        response.setContentLength(content.getBytes().length);
        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }

}