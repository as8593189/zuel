package com.zuel.manage.service;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbItemParam;

/*
 * @author:汪思超
 * @class:商品分类规格服务接口
 * @data:2020.12.13
 * */

public interface TbItemParamServiceApi {

	EasyUIDatagrid<TbItemParam> getTbItemParamByPage(int page, int rows);

    TbItemParam getTbItemParamByItemCatId(Long itemCatId);

    boolean saveTbItemParam(TbItemParam itemParam) throws ServiceException;

    boolean deleteTbItemParamsByIds(Long[] ids) throws ServiceException;
    

}
