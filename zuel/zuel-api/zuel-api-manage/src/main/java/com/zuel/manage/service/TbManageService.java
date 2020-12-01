package com.zuel.manage.service;

import com.zuel.pojo.TbManager;

/*
 * @author:汪思超
 * @Service:后台管理员用户服务接口
 * @date:2020.11.26
 * */

public interface TbManageService {
	/*
	 * @param name:用户姓名
	 *@dao:	依据用户名查询用户
	 *@return :后台管理员用户
	 * */
	
	TbManager findByName(String name);
}
