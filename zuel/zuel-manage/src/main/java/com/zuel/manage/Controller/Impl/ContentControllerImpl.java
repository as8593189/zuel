package com.zuel.manage.Controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.Controller.ContentController;
import com.zuel.manage.service.ContentService;
import com.zuel.pojo.TbContent;

/*
 * 
 * @author:汪思超
 * @service:内容管理服务控制器实现
 * @date:2020.12.14
 * */
@RestController
public class ContentControllerImpl implements ContentController {

	@Autowired
	private ContentService service;
	
	@Override
	@RequestMapping("/content/query/list")
	public EasyUIDatagrid<TbContent> getContentByCategory(Long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		return service.getContentByCategory(categoryId, page, rows);
	}

	@Override
	@RequestMapping("/content/save")
	public ZuelResult saveContent(TbContent content) throws ServiceException {
		// TODO Auto-generated method stub
		return service.saveContent(content);
	}

	@Override
	@RequestMapping("/content/delete")
	public ZuelResult removeContent(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		return service.removeContent(ids);
	}

}
