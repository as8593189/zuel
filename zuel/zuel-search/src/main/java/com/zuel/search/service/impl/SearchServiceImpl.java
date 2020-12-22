package com.zuel.search.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.SearchItem;
import com.zuel.search.service.SearchService;

/*
 * @author:汪思超
 * @class:操作solr服务的实现类
 * @date:2020.12.21
 * */

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
    private SolrTemplate solrTemplate;
	
	@Override
	public Map<String, Object> search(String query, int page, int rows) {
		// TODO Auto-generated method stub
		HighlightQuery searchQuery = new SimpleHighlightQuery();
        // 创建搜索条件q
        Criteria c = new Criteria("ego_item_keyword").is(query);
        // 增加搜索条件
        searchQuery.addCriteria(c);
        // 分页处理
        searchQuery.setOffset((page-1L)*rows);
        searchQuery.setRows(rows);
        // 排序 id降序
        searchQuery.addSort(Sort.by(Sort.Direction.DESC, "id"));
        // 设置高亮
        HighlightOptions options = new HighlightOptions();
        options.setSimplePrefix("<span style='color:red'>");
        options.setSimplePostfix("</span>");
        options.addField("ego_item_title","ego_item_sell_point");
        searchQuery.setHighlightOptions(options);

        HighlightPage<SearchItem> highlightPage =
                solrTemplate.queryForHighlightPage("ego",searchQuery, SearchItem.class);

        // 创建一个结果集合
        List<SearchItem> resultItemList = new ArrayList<>();

        // 获取高亮搜索结果
        List<HighlightEntry<SearchItem>> hlList = highlightPage.getHighlighted();
        for(HighlightEntry<SearchItem> item : hlList){
            SearchItem egoSearchItem = item.getEntity();
            // 处理高亮
            List<HighlightEntry.Highlight> hl = item.getHighlights();
            for(HighlightEntry.Highlight tmp : hl){
                if(tmp.getField().getName().equals("ego_item_title")){
                    // 商品名有高亮
                    egoSearchItem.setTitle(tmp.getSnipplets().get(0));
                }
                if(tmp.getField().getName().equals("ego_item_sell_point")){
                    // 卖点有高亮
                    egoSearchItem.setSellPoint(tmp.getSnipplets().get(0));
                }
            }
            // 把处理后有高亮数据的对象，保存在结果集合中。
            resultItemList.add(egoSearchItem);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("itemList", resultItemList);
        resultMap.put("query", query);
        resultMap.put("page", page);
        resultMap.put("totalPages", highlightPage.getTotalPages());
        return resultMap;
	}

	
}
