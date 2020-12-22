package com.zuel.manage.service;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemParamItem;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品状态码更改服务接口
 * @date:2020.12.6
 * */

public interface TbItemStatusModify{
	
	EasyUIDatagrid<TbItem> getTbItemsByPage(int page, int rows);
	
	public boolean modifyStatus (Long[] ids, Byte status) throws ServiceException;
	
	 boolean saveItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) throws ServiceException;
}
