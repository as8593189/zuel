package com.zuel.passport.service;

import javax.servlet.http.HttpSession;

import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbUser;

/*
 * @author:汪思超
 * @class:用户登录注册服务接口
 * @date:2021.1.6
 * */


public interface PassportService {

	ZuelResult check(String principal, int type);

    
	ZuelResult register(TbUser user) throws ServiceException;


	ZuelResult login(String username, String password, HttpSession session);
	
}
