package com.zuel.manage.service.impl;




import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


import com.zuel.manage.service.TbManageService;
import com.zuel.mapper.TbManagerMapper;
import com.zuel.pojo.TbManager;
import com.zuel.pojo.TbManagerExample;

/*
 * 
 * @author:汪思超
 * @service:后台管理员服务实现类
 * @date:2020.11.26
 * */
@DubboService
public class TbManagerServiceImpl implements TbManageService {

	/*
	 * @mapper:自动注入后台管理用户
	 * */
	@Autowired
	private TbManagerMapper mapper;

	
	/*
	 * @service:根据名字查找管理员用户
	 * */
	@Override
	public TbManager findByName(String name) {
		// TODO Auto-generated method stub
		//建立查询vo
		TbManagerExample example=new TbManagerExample();
		example.createCriteria().andUsernameEqualTo(name);
		List<TbManager> list=mapper.selectByExample(example);
		if (list.size()==1) {
			return list.get(0);
		}
		return null;
	}
	
	

}
