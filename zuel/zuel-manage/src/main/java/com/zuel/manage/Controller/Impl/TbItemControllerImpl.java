package com.zuel.manage.Controller.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelControllerValue;
import com.zuel.common.vo.ZuelPageResult;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.Controller.TbItemController;
import com.zuel.manage.service.TbItemDescService;
import com.zuel.manage.service.TbItemManageService;
import com.zuel.pojo.TbItem;

/*
 * @author:汪思超
 * @class:商品管理服务类控制器
 * @data:2020.12.5
 * */
@RestController
public class TbItemControllerImpl implements TbItemController {

	@Autowired
	private TbItemManageService service;
	
	@Autowired 
	private TbItemDescService deseService;
	
	@Override
	@RequestMapping(value=ZuelControllerValue.Manage+"getItems")
	public ZuelPageResult<TbItem> getItems(int page, int size, String search) {
		// TODO Auto-generated method stub
		System.out.println("我受够了");
		return service.getItems(page, size, search);
	}

	@Override
	@RequestMapping(value=ZuelControllerValue.Manage+"addItem")
	public ZuelResult addItem(TbItem item) {
		// TODO Auto-generated method stub
		return service.addItem(item);
	}

	@Override
	@RequestMapping(value=ZuelControllerValue.Manage+"updateItem")
	public ZuelResult updateItem(TbItem item) throws Exception {
		// TODO Auto-generated method stub
		return service.updateItem(item);
	}

	@Override
	@RequestMapping(value=ZuelControllerValue.Manage+"deleteItem")
	public ZuelResult deleteItem(Long id) throws Exception {
		// TODO Auto-generated method stub
		return service.deleteItem(id);
	}

	@Override
	@RequestMapping(value=ZuelControllerValue.Manage+"underItem")
	public ZuelResult underItem(Long id) throws Exception {
		// TODO Auto-generated method stub
		return service.underItem(id);
	}

	@Override
	@RequestMapping(value=ZuelControllerValue.Manage+"upItem")
	public ZuelResult upItem(Long id) {
		// TODO Auto-generated method stub
		return service.upItem(id);
	}

	@Override
	@RequestMapping(value="/item/list")
	public EasyUIDatagrid<TbItem> getItemsByPage(@RequestParam(value = "page", defaultValue="1") Integer page,
			@RequestParam(value = "rows" ,defaultValue="30") Integer rows) {
		// TODO Auto-generated method stub
		return service.getItemsByPage(page, rows);
	}

	@Override
	@RequestMapping(value="/rest/item/delete")
	public ZuelResult deleteItemByStatus(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		return service.deleteItemByStatus(ids);
	}

	@Override
	@RequestMapping(value="/rest/item/instock")
	public ZuelResult underItem2(Long[] ids) throws Exception {
		// TODO Auto-generated method stub
		return service.underItem2(ids);
	}

	@Override
	@RequestMapping(value="/rest/item/reshelf")
	public ZuelResult upItem2(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		return service.upItem2(ids);
	}

	@Override
	@RequestMapping(value="/rest/item/query/item/desc/{itemId}")
	public ZuelResult getTbitemDescByKey(@PathVariable("itemId") Long itemKey) throws ServiceException {
		// TODO Auto-generated method stub
		return deseService.getTbitemDescByKey(itemKey);
	}

	@Override
	@RequestMapping(value="/item/save")
	public ZuelResult saveItem(TbItem item, String desc, String itemParams, Long itemParamId) throws ServiceException {
		// TODO Auto-generated method stub
		return service.saveItem(item, desc,itemParams,itemParamId);
	}


	
}
