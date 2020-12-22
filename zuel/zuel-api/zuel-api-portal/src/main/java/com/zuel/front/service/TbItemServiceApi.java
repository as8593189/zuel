package com.zuel.front.service;

import java.util.List;

import com.zuel.common.vo.SearchItem;

/*
 * @author:汪思超
 * @class:前台商品服务的api
 * @date:2020.12.22
 * */

public interface TbItemServiceApi {

	List<SearchItem> getItemsByIds(Long[] ids);
}
