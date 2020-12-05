package com.zuel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zuel.pojo.TbManager;

/*
 * @author:汪思超
 * @date：2020.11.25
 * @api:后台管理的api
 * */
@Repository
public interface TbManageDao extends JpaRepository<TbManager,Integer> {
	
	/*
	 *@param name 用户姓名
	 *@dao	依据用户名查询用户，自动findby注解
	 *@return 后台管理员用户
	 * */
	
	TbManager findByUserName(String username);
	
	
	
}
