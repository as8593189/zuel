package com.zuel.search.dao;

import java.util.List;

import com.zuel.common.vo.SearchItem;

/*
 * @author:汪思超
 * @class:操作solr的dao
 * @date:2020.12.21
 * */

public interface SolrDao {

	boolean delete(List<String> ids);
	
    boolean save(List<SearchItem> list);
}
