/************************************************************************
 * 描述 ：数据库表t_callbackurl对应的表单对象，代码生成。
 * 作者 ：ZHAOZD
 * 日期 ：2018-06-14 20:00:38
 *
 ************************************************************************/

package com.yaic.auth.thirdparty.dto;

import java.io.Serializable;

import com.yaic.auth.thirdparty.model.CallbackUrlModel;


/** 
* @ClassName: CallbackUrlDto 
* @Description: TODO
* @author zhaoZD
* @date 2018年6月17日 下午9:52:33 
*  
*/
public class CallbackUrlDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer callbackUrlId;
//	/** 方案id */
//	private Integer projectId;
	/** 环境类型 */
	private String callbackEnv;
	/** 回调类型 */
	private String callbackType;
	/** 回调url */
	private String callbackUrl;
	/** 回调方法 */
	private String callbackMethod;
	
	
	public CallbackUrlDto() {}

	public CallbackUrlDto(CallbackUrlModel model) {
		
		if(model == null){
			model = new CallbackUrlModel();
		}
		
		this.callbackUrlId = model.getCallbackUrlId();
//		this.projectId = model.getProjectId();
		this.callbackEnv = model.getCallbackEnv();
		this.callbackType = model.getCallbackType();
		this.callbackUrl = model.getCallbackUrl();
		this.callbackMethod = model.getCallbackMethod();
	}
	
	
	public Integer getCallbackUrlId() {
		return callbackUrlId;
	}
	public void setCallbackUrlId(Integer callbackUrlId) {
		this.callbackUrlId = callbackUrlId;
	}
//	public Integer getProjectId() {
//		return projectId;
//	}
//	public void setProjectId(Integer projectId) {
//		this.projectId = projectId;
//	}
	public String getCallbackType() {
		return callbackType;
	}
	public String getCallbackEnv() {
		return callbackEnv;
	}
	public void setCallbackEnv(String callbackEnv) {
		this.callbackEnv = callbackEnv;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getCallbackMethod() {
		return callbackMethod;
	}
	public void setCallbackMethod(String callbackMethod) {
		this.callbackMethod = callbackMethod;
	}
	
	
	
}
