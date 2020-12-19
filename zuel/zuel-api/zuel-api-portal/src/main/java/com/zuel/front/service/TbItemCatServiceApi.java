package com.zuel.front.service;

import java.util.List;

import com.zuel.pojo.TbItemCat;

/*
 * 
 * @author:汪思超
 * @service:前台系统商品类型服务标准
 * @date:2020.12.18
 * */


public interface TbItemCatServiceApi {
	
	List<TbItemCat> getItemCatByParent(Long parentId);
}
