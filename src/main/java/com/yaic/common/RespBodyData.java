package com.yaic.common;

import java.util.List;

import com.yaic.auth.thirdparty.dto.InterfaceInfoDto;

public class RespBodyData {
	private String access_token;
	private String refresh_token;
	private Long expires_in;
	private String open_id;
	private String scope;

	private List<InterfaceInfoDto> interface_list;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public List<InterfaceInfoDto> getInterface_list() {
		return interface_list;
	}

	public void setInterface_list(List<InterfaceInfoDto> interface_list) {
		this.interface_list = interface_list;
	}

}
