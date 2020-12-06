package com.zuel.manage.service;

import java.util.List;

import com.zuel.pojo.TbItemCat;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品类型服务
 * @date:2020.12.6
 * */
public interface TbItemCategoryService {

	/*
	 * 获取子节点列表
	 * */
	public List<TbItemCat> getTbItemCatByParentNode(Long parentId);
}
