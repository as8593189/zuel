package com.zuel.manage.Controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.common.vo.EasyUITreeNode;
import com.zuel.manage.Controller.TbItemCatgoryController;
import com.zuel.manage.service.TbItemManageCategoryService;

/**
 * @author 汪思超
 * @Service 后台管理商品类型目录控制器实现
 * @date 2020.12.06
 * */

@RestController
public class TbItemCategoryControllerImpl implements TbItemCatgoryController {

	@Autowired
	TbItemManageCategoryService service;
	
	@Override
	@RequestMapping(value="/item/cat/list")
	public List<EasyUITreeNode> getEasyUIChildrenNode(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		// TODO Auto-generated method stub
		return service.getEasyUIChildrenNode(parentId);
	}

	
}
