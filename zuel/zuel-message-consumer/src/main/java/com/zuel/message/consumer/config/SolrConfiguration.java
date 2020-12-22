package com.zuel.message.consumer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

import com.zuel.search.dao.SolrDao;
import com.zuel.search.dao.impl.SolrDaoImpl;

/*
 * @author:汪思超
 * @class:solr相关配置
 * @date:2020.12.21
 * */


@Configuration
public class SolrConfiguration {
    /**
     * 初始化SolrDao对象。
     * @param solrTemplate
     * @return
     */
    @Bean
    public SolrDao solrDao(SolrTemplate solrTemplate){
        SolrDaoImpl solrDao = new SolrDaoImpl();
        solrDao.setSolrTemplate(solrTemplate);
        return solrDao;
    }
}
