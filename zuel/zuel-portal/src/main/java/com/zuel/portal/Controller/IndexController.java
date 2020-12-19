package com.zuel.portal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 
 * @author:汪思超
 * @app:前台主页跳转控制器
 * @date:2020.12.19
 * */
@Controller
public class IndexController {
	
    /**
     * 首页面跳转方法。
     * @return
     */
    @GetMapping(value = {"/", "/index", "/default", "/welcome"})
    public String toIndex(){
        return "index";
    }
}
