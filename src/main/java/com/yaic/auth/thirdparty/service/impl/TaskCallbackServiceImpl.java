package com.yaic.auth.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.yaic.auth.thirdparty.dao.AuthCallbackInfoDao;
import com.yaic.auth.thirdparty.model.AuthCallbackInfoModel;
import com.yaic.auth.thirdparty.service.TaskCallbackService;
import com.yaic.common.Constants;
import com.yaic.common.util.HttpUtil;
import com.yaic.servicelayer.http.wrapper.HttpResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.yaic.common.Constants.DEAL_BIZ_FAILURE;
import static com.yaic.common.LogFileNameEnum.QUARTZ_TASK;

/**
 * @ClassName : TaskCallbackServiceImpl
 * @Description : 定时任务处理未成功的支付回调
 * @author : jiangxy
 * @date : 2018\7\30  15:01
 */
@Service
public class TaskCallbackServiceImpl implements TaskCallbackService {

    private Logger log = LoggerFactory.getLogger(QUARTZ_TASK.getLogFileName());

	/**
     * 尝试处理失败回调的最大次数
	 */
	private static final int MAX_DEAL_COUNT = 8;

	/**
	 * 任务（处理支付回调失败）线程池
	 * 		corePoolSize(池中核心线程数) : 5个
	 * 		maximumPoolSize(池中最大线程数) : 15个
	 * 		keepAliveTime(池中非核心线程数闲置超时时长) : 5分钟
	 * 		BlockingQueue(任务阻塞队列) : 长度为 100的定长任务队列 ArrayBlockingQueue
	 * 			当接受到任务的时候，如果没有达到corePoolSize的值，则创建新线程（核心线程）执行任务，
	 * 			如果达到了corePoolSize的数量，则进入该队列等候，
	 * 			如果该队列已满，则创建新的线程（非核心线程）执行任务，
	 * 			如果总线程数超过maximumPoolSize定义的数值，则发生错误。
	 * 		ThreadFactory(线程工厂) : 为执行任务的线程分配一个name属性
	 */
	// TODO 线程池配置参数依据生产上的实际情况进行调整
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
			5, 15, 5, TimeUnit.MINUTES,
			new ArrayBlockingQueue<Runnable>(100),
			new ThreadFactory(){
				private final AtomicInteger threadCount = new AtomicInteger(1);
				@Override
				public Thread newThread(Runnable r) {
					return new Thread(r, "T_retryCallback#" + threadCount.getAndIncrement());
				}
			}
	);

	@Autowired
	private AuthCallbackInfoDao callbackInfoDao;

	/**
     * 定时处理未成功的回调
     */
    public String dealQuartzBiz(String dealType) {
	    log.debug("---------------------------重试回调开始--------------------------");
        log.debug("dealType={}", dealType);

	    // 查库，查出所有未执行成功的回调
        Map<String,Object> paramMap = new HashMap<String,Object>();

        paramMap.put("dealType", dealType);

	    List<AuthCallbackInfoModel> unsuccessfulCallBackInfo = callbackInfoDao.selectUnsuccessCallbackInfo(paramMap);
	    log.debug("需要执行重试的任务数量={}", unsuccessfulCallBackInfo.size());

	    for (AuthCallbackInfoModel unsuccessfulInfo: unsuccessfulCallBackInfo) {

		    Integer dealCount = unsuccessfulInfo.getDealCount();

		    if(dealCount <= MAX_DEAL_COUNT){

				TaskProcessor processor = new TaskProcessor(unsuccessfulInfo);

				executor.execute(processor);

		    }else{

		    	unsuccessfulInfo.setInvalidFlag(1);

			    callbackInfoDao.updateByPrimaryKeySelective(unsuccessfulInfo);

		    }
	    }

	    log.debug("---------------------------重试回调结束--------------------------");
    	return "";
    }


	class TaskProcessor implements Runnable{

    	private AuthCallbackInfoModel unsuccessfulInfo;

		private TaskProcessor(AuthCallbackInfoModel unsuccessfulInfo) {
			this.unsuccessfulInfo = unsuccessfulInfo;
		}

		@Override
		public void run() {

			AuthCallbackInfoModel infoModel = (AuthCallbackInfoModel)unsuccessfulInfo.clone();

			// 先更新一次状态，回调后再更新状态
			infoModel.setDealStatus(Constants.DEAL_BIZ_PROCESS);
			infoModel.setUpdatedUser(Constants.UPDATED_USER);
			infoModel.setUpdatedDate(new Date());
			callbackInfoDao.updateByPrimaryKeySelective(infoModel);

			String callbackContent = infoModel.getContent();
			String callbackUrl = infoModel.getCallbackUrl();
			log.debug(Thread.currentThread().getName() + "发送回调POST请求 content：{}, url:{}", callbackContent, callbackUrl);
			HttpResponseWrapper result = HttpUtil.connectServer(callbackContent, callbackUrl, null, null, null);
			log.debug(Thread.currentThread().getName() + "接收到回调请求结果：{}", JSON.toJSONString(result));

			if (result.getStatus()) {
				// 回调成功（趣店回调返回成功值特殊处理）
				if (Constants.SUCCESS_MSG.equals((String)result.getContent())
						|| Constants.QUDIAN_SUCCESS_CODE.equals(JSON.parseObject((String)result.getContent(), Map.class).get("code"))) {
					log.info("重试回调成功");
				    infoModel.setDealStatus(Constants.DEAL_BIZ_SUCCESS);
					infoModel.setDealCount(infoModel.getDealCount() + 1 );
				}  else {
				    log.info("重试回调成功， 渠道处理失败。渠道appId={}, 回调地址={}", infoModel.getAppId(), infoModel.getCallbackUrl());
					infoModel.setDealStatus(DEAL_BIZ_FAILURE);
					infoModel.setDealCount(infoModel.getDealCount() + 1 );
				}
			} else {
			    log.info("重试回调失败， 响应的HTTP状态码不正确。渠道appId={}, 回调地址={}", infoModel.getAppId(), infoModel.getCallbackUrl());
				infoModel.setDealStatus(DEAL_BIZ_FAILURE);
				infoModel.setDealCount(infoModel.getDealCount() + 1 );
			}

			callbackInfoDao.updateByPrimaryKeySelective(infoModel);
		}
	}

}
