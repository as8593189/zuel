package com.zuel.manage.Controller.Impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author:汪思超
 * @class:提供一个视图跳转控制器
 * @data:2020.12.6
 * */
@Controller
public class ViewController {

	@RequestMapping("/{pageName}")
	public String pageDispatcher(@PathVariable("pageName") String pageName) {
		
		return pageName;
	}
}
