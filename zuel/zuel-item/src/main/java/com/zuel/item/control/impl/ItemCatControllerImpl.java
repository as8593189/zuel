package com.zuel.item.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.item.control.ItemCatController;
import com.zuel.item.service.ItemCategoryService;

/*
 * 
 * @author:汪思超
 * @app:商品系统中的商品类型控制器实现类
 * @date:2020.12.18
 * */

@RestController
public class ItemCatControllerImpl implements ItemCatController {

	@Autowired
    private ItemCategoryService service;
	
	@GetMapping(value = "/rest/itemcat/all", produces = "application/json;charset=UTF-8")
    @CrossOrigin
	@Override
	public Object getItemCat() {
		// TODO Auto-generated method stub
		return service.getItemCategory();
	}

}
