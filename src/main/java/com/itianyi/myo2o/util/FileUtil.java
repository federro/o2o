package com.itianyi.myo2o.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {
	private static String seperator = System.getProperty("file.separator");
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	
	public static String getImgBasePath(){
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")){
			basePath = "D:/projectdev/image/";
		}else{
			basePath = "/home/xiangzepro/";
		}
		
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getHeadLineImagePath() {
		String headLineImagePath = "/upload/images/item/headtitle/";
		headLineImagePath = headLineImagePath.replace("/", seperator);
		return headLineImagePath;
	}

	public static String getShopCategoryImagePath() {
		String shopCategoryImagePath = "/upload/images/item/shopcategory/";
		shopCategoryImagePath = shopCategoryImagePath.replace("/", seperator);
		return shopCategoryImagePath;
	}
	
	public static String getPersonInfoImagePath() {
		String personInfoImagePath = "/upload/images/item/personinfo/";
		personInfoImagePath = personInfoImagePath.replace("/", seperator);
		return personInfoImagePath;
	}

	public static String getShopImagePath(long shopId) {
		StringBuilder shopImagePathBuilder = new StringBuilder();
		shopImagePathBuilder.append("/upload/images/item/shop/");
		shopImagePathBuilder.append(shopId);
		shopImagePathBuilder.append("/");
		String shopImagePath = shopImagePathBuilder.toString().replace("/",seperator);
		return shopImagePath;
		//return shopImagePathBuilder.toString();
	}
	public static String getRandomFileName(){
		//生成随机文件名：当前月日分秒+五位随机数
		int rannum = (int)(r.nextDouble()*(99999 - 10000 +1)) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}
	
	public static void deleteFile(String storePath){
		File file = new File(getImgBasePath()+storePath);
		if(file.exists()){
			if(file.isDirectory()){
				File files[] = file.listFiles();
				for(int i=0 ; i< files.length; i++){
					files[i].delete();
				}
			}
			file.delete();
		}
	}
	
	
}
