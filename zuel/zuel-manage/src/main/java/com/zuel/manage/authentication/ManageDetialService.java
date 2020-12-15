package com.zuel.manage.authentication;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zuel.manage.service.TbManageService;
import com.zuel.pojo.TbManager;


/*
 * @author:汪思超
 * @class:security认证实现类
 * @data:2020.12.1
 * */
@Service
public class ManageDetialService implements UserDetailsService {

	@DubboReference
	private TbManageService managerService;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		TbManager manager=managerService.findByName(userName);
		if (manager==null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return new User(manager.getusername(), manager.getPassword(), 
				AuthorityUtils.createAuthorityList("未授权"));
	}

}
