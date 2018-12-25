package com.itianyi.myo2o.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	public static final String DB_MASTER = "master";
	public static final String DB_SLAVE = "slave";
	
	
	public static String getDbType(){
		String db = threadLocal.get();
		if(db == null){
			db = DB_MASTER;		}
		return db;
	}
	
	public static void setDbType(String str){
		logger.debug("所使用的数据源为" + str);
		threadLocal.set(str);
	}
	
	public static void clearDbType(){
		threadLocal.remove();
	}
}