package com.zuel.front.service;

import com.zuel.exception.ServiceException;
import com.zuel.pojo.TbUser;

/*
 * @author:汪思超
 * @class:用户登录服务接口
 * @date:2021.1.6
 * */

public interface TbUserServiceAPI {

	TbUser getUserByParams(TbUser tbUser);

    boolean addUser(TbUser user) throws ServiceException;

    TbUser getUserByUsername(String username, String password);
}
