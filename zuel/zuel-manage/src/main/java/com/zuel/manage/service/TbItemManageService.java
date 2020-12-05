package com.zuel.manage.service;


import com.zuel.common.vo.ZuelPageResult;
import com.zuel.common.vo.ZuelResult;
import com.zuel.pojo.TbItem;

/**
 * @author 汪思超
 * @Service 后台管理商品管理api
 * @date 2020.12.02
 * */


public interface TbItemManageService  {

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
