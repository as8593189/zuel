package com.zuel.cart.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zuel.cart.service.CartService;
import com.zuel.common.vo.ZuelCartItem;
import com.zuel.common.vo.ZuelCookie;
import com.zuel.common.vo.ZuelItemShowObject;

/*
 * @author:汪思超
 * @class:购物车系统服务实现类
 * @date:2021.1.9
 * */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
    @Value("${zuel.cache.front.item.itemBased}")
    private String itemCacheKey;
    
    @Value("${zuel.front.cart.cookie.name}")
    private String cartCookieName;
    
    @Value("${zuel.front.cart.redis.keyPrefix}")
    private String cartRedisKeyPrefix;

    @Override
    public void deleteItemFromCart(Long sku) {
        String cartKey = this.getCartKey();
        Map<String, ZuelCartItem> cart = this.getCartFromRedis(cartKey);
        cart.remove(sku.toString());
        this.setCart2Redis(cartKey, cart);
        this.saveKeyWithCookie(cartKey, 60*60*24*365);
    }


    @Override
    public void updateCartItemNum(Long sku, int num) {
        String cartKey = this.getCartKey();
        Map<String, ZuelCartItem> cart = this.getCartFromRedis(cartKey);
        ZuelCartItem item = cart.get(sku.toString());
        item.setNum(num);
        this.setCart2Redis(cartKey, cart);
        this.saveKeyWithCookie(cartKey, 60*60*24*365);
    }


    @Override
    public Collection<ZuelCartItem> getCart() {
        String cartKey = getCartKey();
        Map<String, ZuelCartItem> cart = this.getCartFromRedis(cartKey);
        return cart.values();
    }



	@SuppressWarnings("unchecked")
	private Map<String, ZuelCartItem> getCartFromRedis(String cartKey){
        Map<String, ZuelCartItem> cart = null;
        cart = (Map<String, ZuelCartItem>) redisTemplate.opsForValue().get(cartKey);
        if(cart == null){
            cart = new HashMap<>();
        }
        return cart;
    }


    private String getCartKey(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String cartKey = ZuelCookie.getCookieValue(request, cartCookieName);
        if(null == cartKey){
            cartKey = cartRedisKeyPrefix + UUID.randomUUID().toString();
        }
        return cartKey;
    }

    private void setCart2Redis(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    private void saveKeyWithCookie(String cookieValue, int maxAge){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        ZuelCookie.setCookie(request, response, cartCookieName, cookieValue, maxAge);
    }

    @Override
    public void add2Cart(Long sku, int num) {
        String itemKey = this.itemCacheKey+"("+sku+")";
        System.out.println(itemKey);
        ZuelItemShowObject cacheItem = (ZuelItemShowObject)redisTemplate.opsForValue().get(itemKey);
        String cartKey = getCartKey();
        Map<String, ZuelCartItem> cart = getCartFromRedis(cartKey);
        ZuelCartItem cartItem = new ZuelCartItem();
        cartItem.setId(cacheItem.getId());
        cartItem.setTitle(cacheItem.getTitle());
        cartItem.setImage(cacheItem.getImage());
        cartItem.setPrice(cacheItem.getPrice());
        cartItem.setNum(num);
        if(cart.containsKey(cartItem.getId().toString())){
            cart.get(cartItem.getId().toString()).addNum(cartItem.getNum());
        }else{
            cart.put(cartItem.getId().toString(), cartItem);
        }
        setCart2Redis(cartKey, cart);
        this.saveKeyWithCookie(cartKey, 60*60*24*365);
    }
}
