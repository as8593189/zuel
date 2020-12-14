package com.zuel.manage.Controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.common.vo.ZuelResult;
import com.zuel.manage.Controller.TbItemParamItemController;
import com.zuel.manage.service.TbItemParamItemService;

/**
 * @author 汪思超
 * @Service 后台商品规格控制器服务
 * @date 2020.12.13
 * */

@RestController
public class TbItemParamItemContollerImpl implements TbItemParamItemController{

	@Autowired
	private TbItemParamItemService service;
	
	@Override
	@RequestMapping("/rest/item/param/item/query/{itemId}")
	public ZuelResult getTbItemParamItemByKey(@PathVariable("itemId") Long Itemid) {
		// TODO Auto-generated method stub
		return service.getTbItemParamItemByKey(Itemid);
	}

}
