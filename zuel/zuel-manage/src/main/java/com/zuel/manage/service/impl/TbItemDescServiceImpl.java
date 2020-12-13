package com.zuel.manage.service.impl;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.ZuelResult;
import com.zuel.manage.service.TbItemDescProviderService;
import com.zuel.manage.service.TbItemDescService;
import com.zuel.pojo.TbItemDesc;

/*
 * 
 * @author:汪思超
 * @service:后台商品描述实现
 * @date:2020.12.13
 * */
@Service
public class TbItemDescServiceImpl implements TbItemDescService {

	@DubboReference
	private TbItemDescProviderService service;

	@Override
	public ZuelResult getTbitemDescByKey(Long itemKey) {
		// TODO Auto-generated method stub
		TbItemDesc desc = service.geTbItemDescByKey(itemKey);
		if (desc==null) {
			return ZuelResult.error();
		}
		return ZuelResult.ok();
	}
	
	
	
}
