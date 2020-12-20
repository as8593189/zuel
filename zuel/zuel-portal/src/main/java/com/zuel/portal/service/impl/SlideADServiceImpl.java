package com.zuel.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.ZuelJason;
import com.zuel.common.vo.ZuelSlideAdVo;
import com.zuel.front.service.TbContentServiceAPI;
import com.zuel.pojo.TbContent;
import com.zuel.portal.service.SlideADService;

/*
 * 
 * @author:汪思超
 * @class:前台系统轮播广告服务实现类
 * @date:2020.12.20
 * */
@Service
public class SlideADServiceImpl implements SlideADService {

	@Value("${zuel.portal.ad.slide.categoryId}")
    private Long slideAdContentCategoryId;
	
    @DubboReference
    private TbContentServiceAPI tbContentServiceAPI;
	
	@Override
	@Cacheable(cacheNames = {"zuel:front:portal:ad:slide"}, key = "'getSlideAd()'")
	public String getSlideAd() {
		// TODO Auto-generated method stub
		 List<TbContent> list = this.tbContentServiceAPI.getContentByCategory(slideAdContentCategoryId);
	        // 定义需要的结果集合
	        List<ZuelSlideAdVo> resultList = new ArrayList<>();
	        // 创建EgoSlideAD类型的构建器
	        ZuelSlideAdVo.Builder builder = new ZuelSlideAdVo.Builder();
	        // 把查询的TbContent类型对象，转换成EgoSlideAD类型的对象。
	        for(TbContent content : list){
	        	ZuelSlideAdVo item = builder.build();
	            item.setSrc(content.getPic());
	            item.setSrcB(content.getPic2());
	            item.setHref(content.getUrl());
	            item.setAlt(content.getTitle());
	            resultList.add(item);
	        }
	        return ZuelJason.object2Json(resultList);
	}

}
