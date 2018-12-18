package com.itianyi.myo2o.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import sun.misc.BASE64Decoder;

public class DESUtils {
	private static Key key;
	private static String KEY_STR = "myKey";
	private static String CHARSETNAME = "UTF-8";
	private static String ALGORITHM = "DES";
	
	public static void main(String[] args) {
		String str = "hello world";
		System.out.println(getEncryptString(str));
	}
	
	static{
		try{
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(KEY_STR.getBytes());
			generator.init(random);
			//给key赋值加密秘钥
			key = generator.generateKey();
			generator = null;
		}catch(Exception e){
			throw new RuntimeException(e.toString());
		}
	}
	
	public static String getEncryptString(String str){
		Base64Encoder base64Encoder = new Base64Encoder();
		try{
			//获取要加密字段的字符流
			byte[] bytes = str.getBytes(CHARSETNAME);
			//加密机
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			//指定加密机的加密方式，加密秘钥
			cipher.init(Cipher.ENCRYPT_MODE, key);
			//开始进行加密工作
			byte[] doFinal = cipher.doFinal(bytes);
			return base64Encoder.encode(doFinal);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static String getDecryptString(String str){
		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			byte[] bytes = base64decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			return new String(doFinal, CHARSETNAME);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	
}
