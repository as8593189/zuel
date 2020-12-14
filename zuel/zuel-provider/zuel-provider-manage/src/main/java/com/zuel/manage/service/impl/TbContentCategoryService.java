package com.zuel.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zuel.common.vo.ZuelContentStatus;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbContentCategoryServiceAPI;
import com.zuel.mapper.TbContentCategoryMapper;
import com.zuel.pojo.TbContentCategory;
import com.zuel.pojo.TbContentCategoryExample;

/*
 * 
 * @author:汪思超
 * @service:后台系统商品分类管理服务
 * @date:2020.12.6
 * */

@DubboService
public class TbContentCategoryService implements TbContentCategoryServiceAPI {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TbContentCategory> getContentCategoryByParent(Long parentId) {
		// TODO Auto-generated method stub
		TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId).andStatusEqualTo(ZuelContentStatus.CONTENT_CATEGORY_OK);
        example.setOrderByClause("sort_order asc, name asc");
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        return list;
	}

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean addContentCategory(TbContentCategory contentCategory) throws ServiceException {
		// TODO Auto-generated method stub
		try{
            int rows = this.tbContentCategoryMapper.insertSelective(contentCategory);
            if(rows == 1){
                TbContentCategory parent =
                        this.tbContentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
                if(parent.getIsParent()){
                    return true;
                }
                parent.setIsParent(true);
                parent.setUpdated(contentCategory.getUpdated()); 
                int updatedRows = this.tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
                if(updatedRows == 1){
                    return true;
                }
            }
            throw new ServiceException("新增内容分类异常。");
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e);
        }
	}

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean modifyContentCategory(TbContentCategory contentCategory) throws ServiceException {
		// TODO Auto-generated method stub
		try{
            int rows = this.tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
            if(rows == 1){
                return true;
            }
            throw new ServiceException("更新内容分类异常");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
	}
	
	/*
	 * 此处提供一个删除结点的方法
	 * **/
	
	@Transactional(rollbackFor = {ServiceException.class})
	private boolean deleteNodesById(Long id, Date updated, Integer status) throws ServiceException {
        TbContentCategory contentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(id);
        if(contentCategory.getIsParent()){
            TbContentCategoryExample example = new TbContentCategoryExample();
            example.createCriteria().andParentIdEqualTo(contentCategory.getId());
            List<TbContentCategory> children = this.tbContentCategoryMapper.selectByExample(example);
            for(TbContentCategory child : children){
                deleteNodesById(child.getId(), updated, status);
            }
            contentCategory.setUpdated(updated);
            contentCategory.setStatus(status); 
            int rows = this.tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
            if(rows == 1) {
                return true;
            }else{
                throw new ServiceException("删除内容分类异常");
            }
        }else{
            contentCategory.setUpdated(updated);
            contentCategory.setStatus(status);
            int rows = this.tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
            if(rows == 1){
                return true;
            }else{
                throw new ServiceException("删除内容分类异常");
            }
        }
    }

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean removeContentCategory(TbContentCategory contentCategory) throws ServiceException {
		// TODO Auto-generated method stub
		try {
            deleteNodesById(contentCategory.getId(), contentCategory.getUpdated(), contentCategory.getStatus());
            contentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(contentCategory.getId());
            TbContentCategoryExample example = new TbContentCategoryExample();
            example.createCriteria()
                    .andParentIdEqualTo(contentCategory.getParentId())
                    .andStatusEqualTo(ZuelContentStatus.CONTENT_CATEGORY_OK);
            List<TbContentCategory> siblings = this.tbContentCategoryMapper.selectByExample(example);
            if (siblings.size() == 0) {
                TbContentCategory parent = new TbContentCategory();
                parent.setId(contentCategory.getParentId());
                parent.setUpdated(contentCategory.getUpdated());
                parent.setIsParent(false);
                int rows = this.tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
                if (rows == 1) {
                    return true;
                }
                throw new ServiceException("删除内容分类异常");
            } else {
                return true;
            }
        }catch (ServiceException e){
            e.printStackTrace();
            throw e;
        }
	}

}
