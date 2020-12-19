package com.zuel.front.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuel.front.service.TbItemCatServiceApi;
import com.zuel.mapper.TbItemCatMapper;
import com.zuel.pojo.TbItemCat;
import com.zuel.pojo.TbItemCatExample;

/*
 * 
 * @author:汪思超
 * @app:前台系统商品类型服务
 * @date:2020.12.18
 * */
@DubboService
public class TbItemCatServiceProvider implements TbItemCatServiceApi {
	
	@Autowired
	private TbItemCatMapper mapper;

	@Override
	public List<TbItemCat> getItemCatByParent(Long parentId) {
		// TODO Auto-generated method stub
		TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("sort_order asc");
        return mapper.selectByExample(example);
	}

}
