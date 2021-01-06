package com.zuel.front.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zuel.exception.ServiceException;
import com.zuel.front.service.TbUserServiceAPI;
import com.zuel.mapper.TbUserMapper;
import com.zuel.pojo.TbUser;
import com.zuel.pojo.TbUserExample;

/*
 * @author:汪思超
 * @class:用户登录服务接口实现
 * @date:2021.1.6
 * */
@DubboService
public class TbUserServiceImpl implements TbUserServiceAPI {

	@Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getUserByUsername(String username, String password) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<TbUser> userList = this.tbUserMapper.selectByExample(example);
        if(userList.size() == 1){
            return userList.get(0);
        }
        return null;
    }


    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public boolean addUser(TbUser user) throws ServiceException {
        try{
            int rows = this.tbUserMapper.insertSelective(user);
            if(rows != 1){ 
                throw new ServiceException("用户注册失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public TbUser getUserByParams(TbUser tbUser) {
        TbUserExample example = new TbUserExample();
        if(null != tbUser.getUsername()){ 
            example.createCriteria().andUsernameEqualTo(tbUser.getUsername());
        }else if(null != tbUser.getPhone()){ 
            example.createCriteria().andPhoneEqualTo(tbUser.getPhone());
        }else if(null != tbUser.getEmail()){ 
            example.createCriteria().andEmailEqualTo(tbUser.getEmail());
        }
        List<TbUser> users = this.tbUserMapper.selectByExample(example);
        if(users.size() == 1){
            return users.get(0);
        }
        return null;
    }
	
	
	
}
