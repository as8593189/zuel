package com.zuel.front.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuel.common.vo.SearchItem;
import com.zuel.front.service.TbItemServiceApi;
import com.zuel.mapper.TbItemMapper;

/*
 * @author:汪思超
 * @class:前台商品服务的提供者
 * @date:2020.12.22
 * */
@DubboService
public class TbItemServiceImpl implements TbItemServiceApi {

	@Autowired
	private TbItemMapper mapper;
	
	@Override
	public List<SearchItem> getItemsByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return mapper.selectByIds(ids);
	}

}
