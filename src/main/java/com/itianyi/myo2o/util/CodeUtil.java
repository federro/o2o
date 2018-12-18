package com.itianyi.myo2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest req){
		String verifyCodeExpected = (String)req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(req, "verifyCodeActual");
		if(verifyCodeActual == null || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)){
			return false;
		}
		return true;
	}
}
