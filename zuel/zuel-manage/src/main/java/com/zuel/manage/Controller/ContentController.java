package com.zuel.manage.Controller;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbContent;

/*
 * 
 * @author:汪思超
 * @service:后台系统内容控制器api
 * @date:2020.12.14
 * */

public interface ContentController {

	/*
	 * 查询
	 * */
	EasyUIDatagrid<TbContent> getContentByCategory(Long categoryId, int page, int rows);
	
	/*
	 * 保存
	 * */
	ZuelResult saveContent(TbContent content) throws ServiceException;
	
	/*
	 * 移除
	 * */
	ZuelResult removeContent(Long[] ids) throws ServiceException;
}
