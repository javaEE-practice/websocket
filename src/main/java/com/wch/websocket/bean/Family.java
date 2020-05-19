package com.wch.websocket.bean;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "family")
public class Family {
	/**
	 * 产品
	 */
	private List<String> products;
	/**
	 * 手机
	 */
	private Map<String, String> mobiles;
	/**
	 * 家庭成员
	 */
	private List<Member> members;
	/**
	 * 宠物
	 */
	private Pet pet;
	
	@Override
	public String toString() {
		return "Family [products=" + products + ", mobiles=" + mobiles + ", members=" + members + ", pet=" + pet + "]";
	}
	
	public List<String> getProducts() {
		return products;
	}
	public void setProducts(List<String> products) {
		this.products = products;
	}
	public Map<String, String> getMobiles() {
		return mobiles;
	}
	public void setMobiles(Map<String, String> mobiles) {
		this.mobiles = mobiles;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
}
