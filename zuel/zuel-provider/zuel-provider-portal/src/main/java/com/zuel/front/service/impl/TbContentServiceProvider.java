package com.zuel.front.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuel.front.service.TbContentServiceAPI;
import com.zuel.mapper.TbContentMapper;
import com.zuel.pojo.TbContent;
import com.zuel.pojo.TbContentExample;

/*
 * 
 * @author:汪思超
 * @service:前台系统对于广告内容管理的服务实现
 * @date:2020.12.20
 * */
@DubboService
public class TbContentServiceProvider  implements TbContentServiceAPI{

	@Autowired
	private TbContentMapper mapper;
	
	@Override
	public List<TbContent> getContentByCategory(Long contentCategoryId) {
		// TODO Auto-generated method stub
		TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(contentCategoryId);
        example.setOrderByClause(" updated desc ");
        return mapper.selectByExample(example);
	}

}
