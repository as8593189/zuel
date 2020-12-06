package com.zuel.manage.service.impl;


import java.util.Arrays;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbItemStatusModify;
import com.zuel.mapper.TbItemMapper;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemExample;

/*
 * 
 * @author:汪思超
 * @service:后台商品状态码改变服务实现类
 * @date:2020.12.6
 * */
@DubboService
public class TbItemServiceImpl implements TbItemStatusModify {

	@Autowired
	private TbItemMapper mapper;
	
	@Override
	@Transactional(rollbackFor= {ServiceException.class})
	public boolean modifyStatus(Long[] ids, Byte status) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			TbItem item = new TbItem();
			item.setStatus(status);
			TbItemExample example = new TbItemExample();
			example.createCriteria().andIdIn(Arrays.asList(ids));
			int rows = mapper.updateByExampleSelective(item, example);
			if (rows == ids.length) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		throw new ServiceException("更新商品状态发送错误，更新的商品主键为："
                + Arrays.toString(ids) + "，更新后的状态是：" + status);
	}

}
