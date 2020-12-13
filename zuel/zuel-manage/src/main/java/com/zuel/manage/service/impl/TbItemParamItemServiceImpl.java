package com.zuel.manage.service.impl;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.ZuelResult;
import com.zuel.manage.service.TbItemParamItemService;
import com.zuel.manage.service.TbItemParamItemServiceApi;

/**
 * @author 汪思超
 * @Service 后台管理的商品规格服务的实现
 * @date 2020.12.13
 * */

@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {

	@DubboReference
	private TbItemParamItemServiceApi service;
	
	@Override
	public ZuelResult getTbItemParamItemByKey(Long Itemid) {
		// TODO Auto-generated method stub
		return  ZuelResult.ok(service.getItemParamItemByKey(Itemid));
	}

	
}
