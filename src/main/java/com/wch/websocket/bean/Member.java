package com.wch.websocket.bean;

public class Member {
	private String name;
	private Integer age;
	private String sex;
	
	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

}
