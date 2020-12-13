package com.zuel.manage.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zuel.common.vo.ZuelIdUtil;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbItemSaveProviderService;
import com.zuel.mapper.TbItemDescMapper;
import com.zuel.mapper.TbItemMapper;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品存储实现
 * @date:2020.12.13
 * */
@DubboService
public class TbItemSaveServiceImpl implements TbItemSaveProviderService {

	@Autowired
	private TbItemDescMapper deseMapper;
	
	@Autowired
	private TbItemMapper itemMapper;

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean saveItem(TbItem item, TbItemDesc itemDesc) {
		// TODO Auto-generated method stub
		if (null == item.getId()) {
			Long itemId = ZuelIdUtil.getId();
			item.setId(itemId);
			itemDesc.setItemId(itemId);
			int rows = itemMapper.insertSelective(item);
			if (rows == 1) {
				int descRows = deseMapper.insertSelective(itemDesc);
				if (descRows == 1) {
					return true;
				}
			}
		}else {
			itemDesc.setItemId(item.getId());
			int row = itemMapper.updateByPrimaryKeySelective(item);
			if (row == 1) {
				int descRow =deseMapper.updateByPrimaryKeySelective(itemDesc);
				if (descRow == 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
