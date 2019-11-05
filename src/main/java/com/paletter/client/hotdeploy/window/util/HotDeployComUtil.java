package com.paletter.client.hotdeploy.window.util;

public class HotDeployComUtil {

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().equals("");
	}
	
	public static boolean isNotNullOrEmpty(String str) {
		return str != null && !str.trim().equals("");
	}
}
