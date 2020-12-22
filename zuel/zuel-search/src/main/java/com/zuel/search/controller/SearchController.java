package com.zuel.search.controller;

import org.springframework.ui.Model;

/*
 * @author:汪思超
 * @class:操作solr服务的控制器接口
 * @date:2020.12.21
 * */


public interface SearchController {

	public String search(String q,int page,int rows,Model model);
}
