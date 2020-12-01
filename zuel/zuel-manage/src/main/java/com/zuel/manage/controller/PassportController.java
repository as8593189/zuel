package com.zuel.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuel.common.vo.ZuelResult;

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
	@GetMapping(value= {"/","/login"})
	public String toLogin() {
		return "login";

	}
	
	/*
	 * 用户登录后跳转到主页，会从这里跳到security认证页面
	 * */	
	@GetMapping(value= {"/index","/main"})
	public String toIndex() {
		return "index";
	}
	
	/*
	 * 由认证页面跳回本页面
	 * */	
	@PostMapping(value= {"/indexSuccess"})
	@ResponseBody
	public ZuelResult toIndexSuccess() {
		return ZuelResult.ok();
		
		
	}
}
