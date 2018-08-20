package com.yaic.auth.thirdparty.service;

public interface CallbackService {


	/**
	 * 回调业务处理
	 * 支付成功后，回调合作方（渠道）
	 * @param RequestContent
	 * @return
	 */
	String dealBiz(String RequestContent);
}
