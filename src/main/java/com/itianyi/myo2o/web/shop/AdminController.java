package com.itianyi.myo2o.web.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shop" , method = { RequestMethod.GET , RequestMethod.POST})
public class AdminController {
	public Map<String,Object> productcatory(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String KaptchaExpected = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_CONFIG_KEY);
		System.out.println(KaptchaExpected);
		modelMap.put("verifyCode", KaptchaExpected);
		return modelMap;
	}
	
	@RequestMapping(value = "/register" , method = RequestMethod.GET)
	private String register(){
		return "shop/register";
	}
	
}
