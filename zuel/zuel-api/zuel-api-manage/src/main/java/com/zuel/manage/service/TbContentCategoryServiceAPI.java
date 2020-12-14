package com.zuel.manage.service;

import java.util.List;

import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbContentCategory;

/*
 * 
 * @author:汪思超
 * @service:后台系统内容分类服务接口
 * @date:2020.12.14
 * */

public interface TbContentCategoryServiceAPI {

    List<TbContentCategory> getContentCategoryByParent(Long parentId);

    boolean addContentCategory(TbContentCategory contentCategory) throws ServiceException;

    boolean modifyContentCategory(TbContentCategory contentCategory) throws ServiceException;

    boolean removeContentCategory(TbContentCategory contentCategory) throws ServiceException;
    
    

}
