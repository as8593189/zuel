package com.zuel.manage.Controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.common.vo.EasyUITreeNode;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.Controller.ContentCategoryController;
import com.zuel.manage.service.ContentCategoryService;
import com.zuel.pojo.TbContentCategory;

/*
 * 
 * @author:汪思超
 * @service:内容分类管理服务控制器实现
 * @date:2020.12.14
 * */
@RestController
public class ContentCategoryControllerImpl implements ContentCategoryController {

	@Autowired
	private ContentCategoryService service;
	
	@Override
	@RequestMapping("/content/category/list")
	public List<EasyUITreeNode> getContentCategoryByParent( @RequestParam(value = "id", defaultValue = "0") Long parentId) {
		// TODO Auto-generated method stub
		return service.getContentCategoryByParent(parentId);
	}

	@Override
	@RequestMapping("/content/category/create")
	public ZuelResult addContentCategory(TbContentCategory contentCategory) throws ServiceException {
		// TODO Auto-generated method stub
		return service.addContentCategory(contentCategory);
	}

	@Override
	@RequestMapping("/content/category/update")
	public ZuelResult modifyContentCategory(TbContentCategory contentCategory) throws ServiceException {
		// TODO Auto-generated method stub
		return service.modifyContentCategory(contentCategory);
	}

	@Override
	@RequestMapping("/content/category/delete")
	public ZuelResult removeContentCategoryById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return service.removeContentCategoryById(id);
	}

}
