package com.zuel.manage.service;

import java.util.List;

import com.zuel.common.vo.EasyUITreeNode;

/**
 * @author 汪思超
 * @Service 后台管理商品类型目录接口
 * @date 2020.12.06
 * */

public interface TbItemManageCategoryService {

	/**
	 * 将dubbo传回的数据变成easyui框架需要的数据结构
	 * */
	List<EasyUITreeNode> getEasyUIChildrenNode(Long parentId);
}
