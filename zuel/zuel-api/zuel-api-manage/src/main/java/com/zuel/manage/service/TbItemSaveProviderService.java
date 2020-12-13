package com.zuel.manage.service;

import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品存储服务
 * @date:2020.12.13
 * */

public interface TbItemSaveProviderService {

	/**
	 * 可以直接用jpa存储，但是为了提升作业b格，使用dubbo
	 * */
	public boolean saveItem(TbItem item, TbItemDesc itemDesc);
}
