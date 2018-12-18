package com.itianyi.myo2o.enums;

public enum EntityEnums {
	Sunday("星期一","20"), Monday("星期二","25");
	
	private String name;
	private String age;
	
	private EntityEnums(String name,String age){
		this.name = name;
		this.age = age;
	}
	
	public static EntityEnums nameof(String name){
		for(EntityEnums entity : values()){
			if(entity.getName() == name){
				return entity;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
	
}
