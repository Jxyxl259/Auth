package com.yaic.auth.common;

import java.util.UUID;

public class UuidUtils {

	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
}
