package com.zuel.manage.service;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbItemParam;

/**
 * @author 汪思超
 * @Service 后台管理类型规格服务
 * @date 2020.12.13
 * */

public interface TbItemParamService {

	/*
	 * 分布式做的分类，其实我非常想用jpa来着，毕竟更简单
	 * 但是作业要求就莫得办法
	 * */
	EasyUIDatagrid<TbItemParam> getTbItemParamByPage(int page, int rows);
	
	/*
	 * 找规格数据
	 * */
	ZuelResult getItemParamByItemCatId(Long itemCatId);
	
	
	/*
	 * 保存规格数据（更新或者添加）
	 * */
	ZuelResult saveItemParam(TbItemParam itemParam, Long itemCatId) throws ServiceException;
	
	
	/*
	 * 删除规格
	 * */
	ZuelResult deleteItemParams(Long[] ids) throws ServiceException;
}
