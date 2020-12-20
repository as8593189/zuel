package com.zuel.manage.service;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbContent;

/*
 * 
 * @author:汪思超
 * @service:后台系统内容服务接口
 * @date:2020.12.14
 * */

public interface TbContentServiceApi {

	EasyUIDatagrid<TbContent> getContentByCategory(Long categoryId, int page, int rows);

    boolean saveContent(TbContent content) throws ServiceException;

    boolean removeContent(Long[] ids) throws ServiceException;
    
    TbContent getContentById(Long id);
}
