package com.zuel.cart.controller.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuel.cart.controller.CartController;
import com.zuel.cart.service.CartService;
import com.zuel.common.vo.ZuelCartItem;
import com.zuel.common.vo.ZuelResult;

/*
 * @author:汪思超
 * @class:购物车系统控制器实现类
 * @date:2021.1.9
 * */
@Controller
public class CartControllerImpl implements CartController {


	@Autowired
    private CartService cartService;

    @PostMapping("/cart/delete/{sku}")
    @ResponseBody
    public ZuelResult deleteItemFromCart(@PathVariable("sku") Long sku){
        this.cartService.deleteItemFromCart(sku);
        return ZuelResult.ok();
    }

    @PostMapping("/cart/update/num/{sku}/{num}")
    @ResponseBody
    public ZuelResult updateCartItemNum(@PathVariable("sku") Long sku, @PathVariable("num") int num){
        cartService.updateCartItemNum(sku, num);
        return ZuelResult.ok();
    }

    @GetMapping("/cart/add/{sku}.html")
    public String add2Cart(@PathVariable("sku") Long sku, int num){
    	System.out.println("进入方法");
        cartService.add2Cart(sku, num);
        System.out.println("离开方法");
        return "cartSuccess";
    }

    @GetMapping("/cart/cart.html")
    public String toCart(Model model){
        Collection<ZuelCartItem> cartList = this.cartService.getCart();
        model.addAttribute("cartList", cartList);
        return "cart";
    }
}
