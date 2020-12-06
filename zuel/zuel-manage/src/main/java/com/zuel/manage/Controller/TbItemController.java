package com.zuel.manage.Controller;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelPageResult;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
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
	 * 	分页查询所有的商品
	 * 匹配easyUI组件
	 * */
	EasyUIDatagrid<TbItem> getItemsByPage(Integer page, Integer rows);
	
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
	 * 	删除商品api(分布式远程/假的删除/仅仅只做标记)
	 * @throws ServiceException 
	 * 
	 * */
	ZuelResult deleteItemByStatus(Long[] ids) throws ServiceException;
	
	/**
	 * 	商品下架api
	 * @throws Exception 
	 * 
	 * */
	ZuelResult underItem(Long id) throws Exception;
	
	/**
	 * 	商品下架api(分布式)
	 * @throws Exception 
	 * 
	 * */
	ZuelResult underItem2(Long[] ids) throws Exception;
	
	/**
	 * 	商品上架api
	 * 
	 * */
	ZuelResult upItem(Long id);
	
	/**
	 * 	商品上架api(分布式)
	 * @throws ServiceException 
	 * 
	 * */
	ZuelResult upItem2(Long[] ids) throws ServiceException;
}
