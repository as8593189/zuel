package com.zuel.passport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/*
 * @author:汪思超
 * @class:用户登录服务视图跳转控制器
 * @date:2021.1.6
 * */
@Controller
public class PassportDispatcherController {

	@GetMapping("/user/showRegister")
    public String toRegisterPage(){
        return "register";
    }
    
    @GetMapping("/user/showLogin")
    public String toLoginPage(@RequestHeader(name = "Referer", required = false) String url, Model model){
        if(url == null){ 
            url = "";
        }
        if(!url.endsWith("/user/showRegister")) { 
            model.addAttribute("redirect", url);
        }
        return "login";
    }
	
}
