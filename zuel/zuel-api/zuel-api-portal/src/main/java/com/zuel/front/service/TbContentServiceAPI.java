package com.zuel.front.service;

import java.util.List;

import com.zuel.pojo.TbContent;

/*
 * 
 * @author:汪思超
 * @service:前台系统对于广告内容管理的api
 * @date:2020.12.20
 * */

public interface TbContentServiceAPI {

	List<TbContent> getContentByCategory(Long contentCategoryId);
}
