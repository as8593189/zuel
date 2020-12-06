package com.zuel.manage.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuel.manage.service.TbItemCategoryService;
import com.zuel.mapper.TbItemCatMapper;
import com.zuel.pojo.TbItemCat;
import com.zuel.pojo.TbItemCatExample;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品类型服务实现类
 * @date:2020.12.6
 * */
@DubboService
public class TbItemCategoryServiceImpl implements TbItemCategoryService {

	@Autowired
	private TbItemCatMapper mapper;
	
	@Override
	public List<TbItemCat> getTbItemCatByParentNode(Long parentId) {
		// TODO Auto-generated method stub
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCats=mapper.selectByExample(example);
		return tbItemCats;
	}

}
