package com.wch.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wch.websocket.bean.Family;

@Component
@RestController
@RequestMapping("test")
public class TestController {
	@Autowired
	private Family family;
	
	@RequestMapping("/testYml")
	public String testYml() {
		System.out.println("testYml---------"+family);
		return family.toString();
	}

}
