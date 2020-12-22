package com.zuel.search.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zuel.search.controller.SearchController;
import com.zuel.search.service.SearchService;

/*
 * @author:汪思超
 * @class:操作solr服务的控制器实现类
 * @date:2020.12.21
 * */

@Controller
public class SearchControllerImpl implements SearchController {

	@Autowired
    private SearchService searchService;
	
	@GetMapping("/search.html")
	@Override
	public String search(String q,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "20") int rows,
            Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> result = this.searchService.search(q, page, rows);
        model.addAllAttributes(result);
        return "search";
	}

	
}
