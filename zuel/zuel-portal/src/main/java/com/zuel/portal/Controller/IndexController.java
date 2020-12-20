package com.zuel.portal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zuel.portal.service.SlideADService;

/*
 * 
 * @author:汪思超
 * @app:前台主页跳转控制器
 * @date:2020.12.19
 * */
@Controller
public class IndexController {
	
	@Autowired
    private SlideADService slideADService;
	
    /**
     * 首页面跳转方法。
     * @return
     */
    @GetMapping(value = {"/", "/index", "/default", "/welcome"})
    public String toIndex(Model model){
    	String slideAd = slideADService.getSlideAd();
        model.addAttribute("ad1", slideAd);
        return "index";
    }
}
