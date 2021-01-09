package com.zuel.passport.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import com.zuel.common.vo.ZuelCartItem;
import com.zuel.common.vo.ZuelCookie;
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
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
    @Value("${zuel.front.cart.cookie.name}")
    private String cartCookieName; 
    
    @Value("${zuel.front.cart.redis.keyPrefix}")
    private String cartRedisKeyPrefix; 

    @SuppressWarnings("unchecked")
	@Override
    public ZuelResult login(String username, String password, HttpSession session) {
        password = ZuelUserPasswordDigest.digest(password);
        TbUser user = this.tbUserServiceAPI.getUserByUsername(username, password);
        if(null == user){
            return ZuelResult.error("用户名或密码错误");
        }
        user.setPassword(""); 
        session.setAttribute("zuelLoginUser", user);
        String userCartKey = cartRedisKeyPrefix + user.getId().toString();
        Map<String, ZuelCartItem> userCart = (Map<String, ZuelCartItem>)redisTemplate.opsForValue().get(userCartKey);
        if(userCart == null){ 
            userCart = new HashMap<>();
        }
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        String tmpCartKey = ZuelCookie.getCookieValue(request, cartCookieName);
        Map<String, ZuelCartItem> tmpCart = (Map<String, ZuelCartItem>)redisTemplate.opsForValue().get(tmpCartKey);
        if(null == tmpCart){ 
            tmpCart = new HashMap<>();
        }
        for(ZuelCartItem item : tmpCart.values()){ 
        	ZuelCartItem userCartItem = userCart.get(item.getId().toString());
            if(null == userCartItem){ 
                userCart.put(item.getId().toString(), item);
            }else{ 
                userCartItem.addNum(item.getNum());
            }
        }
        redisTemplate.delete(tmpCartKey); 
        redisTemplate.opsForValue().set(userCartKey, userCart); 
        ZuelCookie.setCookie(request, response, cartCookieName, userCartKey, 0);
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
