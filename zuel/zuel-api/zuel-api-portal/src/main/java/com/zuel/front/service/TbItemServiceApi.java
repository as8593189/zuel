package com.zuel.front.service;

import java.util.List;

import com.zuel.common.vo.SearchItem;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemParamItem;

/*
 * @author:汪思超
 * @class:前台商品服务的api
 * @date:2020.12.22
 * */

public interface TbItemServiceApi {

	List<SearchItem> getItemsByIds(Long[] ids);
	
	TbItem getItemBySKU(Long sku);

    TbItemDesc getItemDescBySKU(Long sku);

    TbItemParamItem getItemParamBySKU(Long sku);
}
