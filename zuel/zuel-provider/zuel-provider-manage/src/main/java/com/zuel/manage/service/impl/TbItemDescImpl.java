package com.zuel.manage.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuel.manage.service.TbItemDescProviderService;
import com.zuel.mapper.TbItemDescMapper;
import com.zuel.pojo.TbItemDesc;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品详情获取实现
 * @date:2020.12.13
 * */
@DubboService
public class TbItemDescImpl implements TbItemDescProviderService {

	@Autowired
	private TbItemDescMapper mapper;
	
	@Override
	public TbItemDesc geTbItemDescByKey(Long itemId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(itemId);
	}

	
	
}
