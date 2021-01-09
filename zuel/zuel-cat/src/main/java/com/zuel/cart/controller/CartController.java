package com.zuel.cart.controller;

import org.springframework.ui.Model;
import com.zuel.common.vo.ZuelResult;

/*
 * @author:汪思超
 * @class:购物车系统控制器
 * @date:2021.1.9
 * */

public interface CartController {

	public ZuelResult deleteItemFromCart(Long sku);
	
	public ZuelResult updateCartItemNum(Long sku, int num);
	
	public String add2Cart(Long sku, int num);
	
	public String toCart(Model model);
}
