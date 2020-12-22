package com.zuel.search.service;

import java.util.Map;

/*
 * @author:汪思超
 * @class:操作solr的服务接口
 * @date:2020.12.21
 * */

public interface SearchService {

	
	/**
	 * 分页搜索
	 * @param query 搜索条件
	 * @param page 页数
	 * @param rows 行数
	 * @return query,page,totalPages,itemList
	 * */
	Map<String, Object> search(String query, int page, int rows);
}
