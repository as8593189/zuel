package com.zuel.common.vo;

import com.zuel.pojo.TbItemParam;

/*
 * @author:汪思超
 * @class:商品规格子类，用于查询
 * @data:2020.12.13
 * */

public class TbItemParamSubPage extends TbItemParam  {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private String itemCatName;

    public String getItemCatName() {
        return itemCatName;
    }

    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }
}
