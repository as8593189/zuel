package com.zuel.item.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zuel.front.service.TbItemCatServiceApi;
import com.zuel.item.service.ItemCategoryService;
import com.zuel.pojo.TbItemCat;

/*
 * 
 * @author:汪思超
 * @app:商品系统中的商品类型服务实现类
 * @date:2020.12.18
 * */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
	
	@DubboReference
	private TbItemCatServiceApi catService;

	@Override
	@Cacheable(cacheNames="zuel:front:item:portal",key="'getItemCategory()'")
	public Map<String, Object> getItemCategory() {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<>();
		List<Object> datas = getItemCategoryByParent(0L);
		//前端页面中最多13层
		for(int i = datas.size()-1; i > 13; i--){
            datas.remove(i);
        }
        result.put("data", datas);
		return result;
	}

	
	private List<Object> getItemCategoryByParent(Long parentId){
        List<Object> list = new ArrayList<>();
        List<TbItemCat> children = catService.getItemCatByParent(parentId);
        for(TbItemCat child : children){
            if(!child.getIsParent()){
                list.add("/products/"+child.getId()+".html|"+child.getName());
            } else { 
                Map<String, Object> node = new HashMap<>();
                node.put("u", "/products/"+child.getId()+".html");
                if(child.getParentId().equals(0L)) { 
                    node.put("n", "<a href='/products/"+child.getId()+".html'>"+child.getName()+"</a>");
                } else {  
                    node.put("n", child.getName());
                }
                node.put("i", getItemCategoryByParent(child.getId()));
                list.add(node);
            }
        }
        return list;
    }
}
