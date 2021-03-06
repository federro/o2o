package com.itianyi.myo2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	//对properties中的jdbc属性进行加密
	private String[] encryptPropNames = {"jdbc.username","jdbc.password"};
	
	protected String convertProperty(String propertyName,String propertyValue){
		if(isEncryptProp(propertyName)){
			String decryptValue = DESUtils.getDecryptString(propertyValue);
			return decryptValue;
		}else{
			return propertyValue;
		}
	}
	
	private boolean isEncryptProp(String propertyName){
		for(String encryptpropertyName : encryptPropNames){
			if(encryptpropertyName.equals(propertyName)){
				return true;
			}
		}
		return false;
	}
}
