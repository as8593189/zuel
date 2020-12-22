package com.zuel.manage.service.impl;


import java.util.Arrays;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbItemStatusModify;
import com.zuel.mapper.TbItemDescMapper;
import com.zuel.mapper.TbItemMapper;
import com.zuel.mapper.TbItemParamItemMapper;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemExample;
import com.zuel.pojo.TbItemParamItem;

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
	
	@Autowired
    private TbItemDescMapper tbItemDescMapper;
	
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
	
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

	@Override
	public EasyUIDatagrid<TbItem> getTbItemsByPage(int page, int rows) {
		PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        example.setOrderByClause("id desc");
        List<TbItem> itemList = mapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        EasyUIDatagrid<TbItem> result = new EasyUIDatagrid<>();
        result.setTotal(pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
	}

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean saveItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) throws ServiceException {
		// TODO Auto-generated method stub
		if(null != item.getCreated()) {
            int itemUpdatedRows = mapper.insertSelective(item);
            if(itemUpdatedRows == 1){
                int itemDescUpdatedRows = tbItemDescMapper.insertSelective(itemDesc);
                if(itemDescUpdatedRows == 1){
                    int itemParamItemUpdatedRows = tbItemParamItemMapper.insertSelective(itemParamItem);
                    if(itemParamItemUpdatedRows == 1){
                        return true;
                    }
                }
            }
        } else {
            itemDesc.setItemId(item.getId());
            int itemUpdatedRows = mapper.updateByPrimaryKeySelective(item);
            if(itemUpdatedRows == 1){
                int itemDescUpdatedRows = tbItemDescMapper.updateByPrimaryKeySelective(itemDesc);
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
