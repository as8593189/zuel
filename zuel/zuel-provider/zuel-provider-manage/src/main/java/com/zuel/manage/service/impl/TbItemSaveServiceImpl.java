package com.zuel.manage.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbItemSaveProviderService;
import com.zuel.mapper.TbItemDescMapper;
import com.zuel.mapper.TbItemMapper;
import com.zuel.mapper.TbItemParamItemMapper;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemParamItem;

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

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean saveItem(TbItem item, TbItemDesc itemDesc,TbItemParamItem itemParamItem) throws ServiceException {
		// TODO Auto-generated method stub
		if(null != item.getCreated()) {
            int itemUpdatedRows = itemMapper.insertSelective(item);
            if(itemUpdatedRows == 1){
                int itemDescUpdatedRows = deseMapper.insertSelective(itemDesc);
                if(itemDescUpdatedRows == 1){
                    int itemParamItemUpdatedRows = tbItemParamItemMapper.insertSelective(itemParamItem);
                    if(itemParamItemUpdatedRows == 1){
                        return true;
                    }
                }
            }
        } else {
            itemDesc.setItemId(item.getId());
            int itemUpdatedRows = itemMapper.updateByPrimaryKeySelective(item);
            if(itemUpdatedRows == 1){
                int itemDescUpdatedRows = deseMapper.updateByPrimaryKeySelective(itemDesc);
                if(itemDescUpdatedRows == 1) {
                    int itemParamItemUpdatedRows = tbItemParamItemMapper.updateByPrimaryKeySelective(itemParamItem);
                    if(itemParamItemUpdatedRows == 1){
                        return true;
                    }
                }
            }
        }
        throw new ServiceException("保存商品数据时，发生异常");
	}
}
