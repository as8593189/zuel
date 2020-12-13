package com.zuel.manage.service;

import com.zuel.pojo.TbItemDesc;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品详情获取服务
 * @date:2020.12.13
 * */

public interface TbItemDescProviderService {

	TbItemDesc geTbItemDescByKey(Long itemId);
}
