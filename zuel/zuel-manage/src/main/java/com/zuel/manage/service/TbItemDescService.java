package com.zuel.manage.service;

import com.zuel.common.vo.ZuelResult;

/*
 * 
 * @author:汪思超
 * @service:后台商品藐视api
 * @date:2020.12.13
 * */

public interface TbItemDescService {

	ZuelResult getTbitemDescByKey(Long itemKey);
}
