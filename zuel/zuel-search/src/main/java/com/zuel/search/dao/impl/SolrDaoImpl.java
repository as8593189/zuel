package com.zuel.search.dao.impl;

import java.util.List;

import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.data.solr.core.SolrTemplate;

import com.zuel.common.vo.SearchItem;
import com.zuel.search.dao.SolrDao;

/*
 * @author:汪思超
 * @class:操作solr的实现类
 * @date:2020.12.21
 * */

public class SolrDaoImpl implements SolrDao {

	private SolrTemplate solrTemplate;
	
	@Override
	public boolean delete(List<String> ids) {
		// TODO Auto-generated method stub
		UpdateResponse response = this.solrTemplate.deleteByIds("ego", ids);
        solrTemplate.commit("ego");
        return response.getStatus() == 0;
	}

	@Override
	public boolean save(List<SearchItem> list) {
		// TODO Auto-generated method stub
		System.out.println("集合"+list);
		UpdateResponse response = solrTemplate.saveBeans("ego", list);
        // 提交事务
        solrTemplate.commit("ego");
        return response.getStatus() == 0;
	}

	public SolrTemplate getSolrTemplate() {
		return solrTemplate;
	}

	public void setSolrTemplate(SolrTemplate solrTemplate) {
		this.solrTemplate = solrTemplate;
	}

	
}
