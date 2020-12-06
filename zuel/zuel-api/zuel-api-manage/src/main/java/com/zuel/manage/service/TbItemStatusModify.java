package com.zuel.manage.service;

import com.zuel.exception.ServiceException;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品状态码更改服务接口
 * @date:2020.12.6
 * */

public interface TbItemStatusModify{
	public boolean modifyStatus (Long[] ids, Byte status) throws ServiceException;
}
