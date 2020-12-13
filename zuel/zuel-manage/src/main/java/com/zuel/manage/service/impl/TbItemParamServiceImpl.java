package com.zuel.manage.service.impl;

import java.util.Date;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbItemParamService;
import com.zuel.manage.service.TbItemParamServiceApi;
import com.zuel.pojo.TbItemParam;

/**
 * @author 汪思超
 * @Service 后台管理的商品类型规格服务的实现
 * @date 2020.12.13
 * */
@Service
public class TbItemParamServiceImpl implements TbItemParamService {

	@DubboReference
	private TbItemParamServiceApi service;
	
	@Override
	public EasyUIDatagrid<TbItemParam> getTbItemParamByPage(int page, int rows) {
		// TODO Auto-generated method stub
		return service.getTbItemParamByPage(page, rows);
	}

	@Override
	public ZuelResult getItemParamByItemCatId(Long itemCatId) {
		// TODO Auto-generated method stub
		return ZuelResult.ok(service.getTbItemParamByItemCatId(itemCatId));
	}

	@Override
	public ZuelResult saveItemParam(TbItemParam itemParam, Long itemCatId) throws ServiceException {
		// TODO Auto-generated method stub
		try {
            itemParam.setItemCatId(itemCatId);
            Date now = new Date();
            if (itemParam.getId() == null) { 
                itemParam.setCreated(now);
            }
            itemParam.setUpdated(now);
            boolean isSaved = service.saveTbItemParam(itemParam);
            if(isSaved){
                return ZuelResult.ok();
            }
        }catch(ServiceException e){
            e.printStackTrace();
            throw e; 
        }
        return ZuelResult.error();
	}

	@Override
	public ZuelResult deleteItemParams(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		 try {
	            boolean isDeleted = this.service.deleteTbItemParamsByIds(ids);
	            if (isDeleted) {
	                return ZuelResult.ok();
	            }
	        }catch (ServiceException e){
	            e.printStackTrace();
	            throw e;
	        }
	        return ZuelResult.error();
	}

}
