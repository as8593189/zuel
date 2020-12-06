package com.zuel.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.EasyUITreeNode;
import com.zuel.manage.service.TbItemCategoryService;
import com.zuel.manage.service.TbItemManageCategoryService;
import com.zuel.pojo.TbItemCat;

/**
 * @author 汪思超
 * @Service 后台管理商品类型目录服务实现类
 * @date 2020.12.06
 * */
@Service
public class TbItemmanageCategoryServiceImpl implements TbItemManageCategoryService {

	@DubboReference
	private TbItemCategoryService service;
	
	@Override
	public List<EasyUITreeNode> getEasyUIChildrenNode(Long parentId) {
		// TODO Auto-generated method stub
		List<EasyUITreeNode> nodes=new ArrayList<EasyUITreeNode>();
		List<TbItemCat> cats=service.getTbItemCatByParentNode(parentId);
		for (int i = 0; i < cats.size(); i++) {
			EasyUITreeNode oneNode=new EasyUITreeNode();
			oneNode.setId(cats.get(i).getId());
			oneNode.setText(cats.get(i).getName());
			//父节点暂时关闭，子节点处于打开状态
			oneNode.setState(cats.get(i).getIsParent()?"closed":"open");
			nodes.add(oneNode);
		}
		return nodes;
	}

	
	
}
