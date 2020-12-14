package com.zuel.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.EasyUITreeNode;
import com.zuel.common.vo.ZuelContentStatus;
import com.zuel.common.vo.ZuelIdUtil;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.ContentCategoryService;
import com.zuel.manage.service.TbContentCategoryServiceAPI;
import com.zuel.pojo.TbContentCategory;

/*
 * 
 * @author:汪思超
 * @service:内容分类管理服务实现类
 * @date:2020.12.14
 * */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@DubboReference
	private TbContentCategoryServiceAPI service;
	
	@Override
	public List<EasyUITreeNode> getContentCategoryByParent(Long parentId) {
		// TODO Auto-generated method stub
		List<TbContentCategory> list = service.getContentCategoryByParent(parentId);
        List<EasyUITreeNode> result = new ArrayList<>();
        for(TbContentCategory contentCategory : list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed" : "open");
            result.add(node);
        }
        return result;
	}

	@Override
	public ZuelResult addContentCategory(TbContentCategory contentCategory) throws ServiceException {
		// TODO Auto-generated method stub
		try {
            contentCategory.setId(ZuelIdUtil.getId());
            contentCategory.setIsParent(false);
            contentCategory.setSortOrder(1);
            contentCategory.setStatus(ZuelContentStatus.CONTENT_CATEGORY_OK);
            Date now = new Date();
            contentCategory.setCreated(now);
            contentCategory.setUpdated(now);
            boolean isCreated = service.addContentCategory(contentCategory);
            if (isCreated) { 
                return ZuelResult.ok(contentCategory);
            }
        }catch (ServiceException e){
            e.printStackTrace();
            throw e; 
        }
        return ZuelResult.error("服务器忙，请稍后重试！");
	}

	@Override
	public ZuelResult modifyContentCategory(TbContentCategory contentCategory) throws ServiceException {
		// TODO Auto-generated method stub
		try{
            contentCategory.setUpdated(new Date());
            boolean isModified = service.modifyContentCategory(contentCategory);
            if(isModified){ 
                return ZuelResult.ok();
            }
        }catch(ServiceException e){
            e.printStackTrace();
            throw e;
        }
        return ZuelResult.error("服务器忙，请稍后重试");
	}

	@Override
	public ZuelResult removeContentCategoryById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
            TbContentCategory contentCategory = new TbContentCategory();
            contentCategory.setId(id);
            contentCategory.setUpdated(new Date());
            contentCategory.setStatus(ZuelContentStatus.CONTENT_CATEGORY_DELETED);
            boolean isDeleted = service.removeContentCategory(contentCategory);
            if (isDeleted) {
                return ZuelResult.ok();
            }
        }catch (ServiceException e){
            e.printStackTrace();
            throw e;
        }
        return ZuelResult.error("服务器忙，请稍后重试");
	}

}
