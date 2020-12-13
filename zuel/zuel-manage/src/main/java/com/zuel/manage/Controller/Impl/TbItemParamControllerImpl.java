package com.zuel.manage.Controller.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.Controller.TbItemParamController;
import com.zuel.manage.service.TbItemParamService;
import com.zuel.pojo.TbItemParam;

/**
 * @author 汪思超
 * @Service 后台商品类型规格控制器服务
 * @date 2020.12.13
 * */

@RestController
public class TbItemParamControllerImpl implements TbItemParamController {

	@Autowired
	private TbItemParamService service;
	
	@Override
	@RequestMapping("/item/param/list")
	public EasyUIDatagrid<TbItemParam> getTbItemParamByPage(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "30") int rows) {
		// TODO Auto-generated method stub
		return service.getTbItemParamByPage(page, rows);
	}

	@Override
	@RequestMapping("/item/param/query/itemcatid/{itemCatId}")
	public ZuelResult getItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId) {
		// TODO Auto-generated method stub
		return service.getItemParamByItemCatId(itemCatId);
	}

	@Override
	@RequestMapping("/item/param/save/{itemCatId}")
	public ZuelResult saveItemParam(TbItemParam itemParam, Long itemCatId) throws ServiceException {
		// TODO Auto-generated method stub
		return service.saveItemParam(itemParam, itemCatId);
	}

	@Override
	@RequestMapping("/item/param/delete")
	public ZuelResult deleteItemParams(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		return service.deleteItemParams(ids);
	}

}
