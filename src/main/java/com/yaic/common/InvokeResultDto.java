package com.yaic.common;

/**
 * @Description: 内部方法调用返回对象
 * @author: jiangxy
 * @date: 2018-7-25  13:59
 */
public class InvokeResultDto {

	/** 应答码*/
	private String code;
	/** 应答信息 */
	private String message;
	/** 状态标识码 */
	private String statusCode;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}