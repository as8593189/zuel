package com.zuel.cart.service;

import java.util.Collection;

import com.zuel.common.vo.ZuelCartItem;

/*
 * @author:汪思超
 * @class:购物车系统服务接口
 * @date:2021.1.9
 * */

public interface CartService {

	void add2Cart(Long sku, int num);


    Collection<ZuelCartItem> getCart();


    void updateCartItemNum(Long sku, int num);


    void deleteItemFromCart(Long sku);
}
