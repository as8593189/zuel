package com.zuel.manage.Controller;

import com.zuel.common.vo.ZuelPageResult;
import com.zuel.common.vo.ZuelResult;
import com.zuel.pojo.TbItem;

/*
 * @author:汪思超
 * @class:商品管理服务类控制器接口
 * @data:2020.12.5
 * */

public interface TbItemController {

	/**
	 * 	分页查询所有的商品
	 * 
	 * */
	ZuelPageResult<TbItem> getItems(int page,int size,String search);
	
	
	/**
	 * 	添加商品api
	 * 
	 * */
	ZuelResult addItem(TbItem item);
	
	/**
	 * 	修改商品api
	 * @throws Exception 
	 * 
	 * */
	ZuelResult updateItem(TbItem item) throws Exception;
	
	/**
	 * 	删除商品api
	 * @throws Exception 
	 * 
	 * */
	ZuelResult deleteItem(Long id) throws Exception;
	
	/**
	 * 	商品下架api
	 * @throws Exception 
	 * 
	 * */
	ZuelResult underItem(Long id) throws Exception;
	
	/**
	 * 	商品上架api
	 * 
	 * */
	ZuelResult upItem(Long id);
}
