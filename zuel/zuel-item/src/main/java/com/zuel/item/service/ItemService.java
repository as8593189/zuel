package com.zuel.item.service;

import com.zuel.common.vo.ZuelItemShowObject;

/*
 * @author:汪思超
 * @class:前台的商品服务接口
 * @date:2021.1.5
 * */

public interface ItemService {

	ZuelItemShowObject getItemBySKU(Long sku);

    String getItemDescBySKU(Long sku);

    String getItemParamBySKU(Long sku);
}
