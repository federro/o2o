package com.itianyi.myo2o.enums;

public class EntityEnumsTest {
	public static void main(String[] args) {
		String name = EntityEnums.Monday.getName();
		if("星期一".equals(name)){
			System.out.println(EntityEnums.Monday.getAge());
		}else {
			System.out.println("查无此人");
		}
		
	}
}
