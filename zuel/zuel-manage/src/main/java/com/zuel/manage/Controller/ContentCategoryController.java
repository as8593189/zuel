package com.zuel.manage.Controller;

import java.util.List;

import com.zuel.common.vo.EasyUITreeNode;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbContentCategory;

/*
 * 
 * @author:汪思超
 * @service:后台系统内容分类服务控制器
 * @date:2020.12.14
 * */

public interface ContentCategoryController {

	/*
	 * 查询，父查子
	 * */
	List<EasyUITreeNode> getContentCategoryByParent(Long parentId);
	
	/*
	 * 添加
	 * */
	ZuelResult addContentCategory(TbContentCategory contentCategory) throws ServiceException;
	
	/*
	 * 更新分类
	 * */
	ZuelResult modifyContentCategory(TbContentCategory contentCategory) throws ServiceException;
	
	/*
	 * 标记删除分类
	 * */
	ZuelResult removeContentCategoryById(Long id) throws ServiceException;
}
