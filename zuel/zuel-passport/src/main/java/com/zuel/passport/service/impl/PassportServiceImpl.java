package com.zuel.passport.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.ZuelIdUtil;
import com.zuel.common.vo.ZuelResult;
import com.zuel.common.vo.ZuelUserPasswordDigest;
import com.zuel.exception.ServiceException;
import com.zuel.front.service.TbUserServiceAPI;
import com.zuel.passport.service.PassportService;
import com.zuel.pojo.TbUser;

/*
 * @author:汪思超
 * @class:用户登录服务实现类
 * @date:2021.1.6
 * */
@Service
public class PassportServiceImpl implements PassportService {

	@DubboReference
	private TbUserServiceAPI tbUserServiceAPI;
	
	@Override
    public ZuelResult login(String username, String password, HttpSession session) {
        password = ZuelUserPasswordDigest.digest(password);
        TbUser user = this.tbUserServiceAPI.getUserByUsername(username, password);
        if(null == user){ 
            return ZuelResult.error("用户名或密码错误");
        }
        user.setPassword(""); 
        session.setAttribute("egoLoginUser", user);
        return ZuelResult.ok();
    }

    @Override
    public ZuelResult register(TbUser user) throws ServiceException {
        user.setId(ZuelIdUtil.getId());
        Date now = new Date();
        user.setCreated(now);
        user.setUpdated(now);
        user.setPassword(ZuelUserPasswordDigest.digest(user.getPassword()));
        ZuelResult result = null;
        try{
            boolean isCreated = tbUserServiceAPI.addUser(user);
            if(isCreated){
                result = ZuelResult.ok();
            }else{
                result = ZuelResult.error("服务器忙，请稍后重试");
            }
        }catch(ServiceException e){
            e.printStackTrace();
            throw e;
        }
        return result;
    }


    @Override
    public ZuelResult check(String principal, int type) {
        TbUser tbUser = new TbUser();
        if (1 == type) { 
            tbUser.setUsername(principal);
        } else if (2 == type) { 
            tbUser.setPhone(principal);
        } else if (3 == type) { 
            tbUser.setEmail(principal);
        }else{ 
            return ZuelResult.error();
        }
        TbUser getResult = this.tbUserServiceAPI.getUserByParams(tbUser);
        if(null == getResult){
            return ZuelResult.ok();
        }
        return ZuelResult.error();
    }
	
}
