package com.zuel.manage.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuel.manage.service.TbItemParamItemServiceApi;
import com.zuel.mapper.TbItemParamItemMapper;
import com.zuel.pojo.TbItemParamItem;
import com.zuel.pojo.TbItemParamItemExample;

/*
 * @author:汪思超
 * @class:商品规格服务实现
 * @data:2020.12.13
 * */
@DubboService
public class TbItemParamItemServiceImpl implements TbItemParamItemServiceApi {

	@Autowired
    private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItemParamItem getItemParamItemByKey(Long itemId) {
		// TODO Auto-generated method stub
		TbItemParamItemExample example=new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list.size()==1) {
			return list.get(0);
		}
		return null;
	}

}
