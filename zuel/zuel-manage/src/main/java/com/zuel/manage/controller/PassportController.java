package com.zuel.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author:汪思超
 * @class:作为后台登录的控制器
 * @date:2020.11.23
 * */

@Controller
public class PassportController {

	/*
	 * 处理登录请求
	 * */
	@RequestMapping(value= {"/","/login"})
	public String toLogin() {
		return "login";

	}
	
	/*
	 * 用户登录后跳转到主页
	 * */	
	@RequestMapping(value= {"/index"})
	public String toIndex() {
		return "index";
		
		
	}
}
