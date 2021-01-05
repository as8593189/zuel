package com.zuel.item.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuel.common.vo.ZuelItemShowObject;
import com.zuel.item.control.ItemController;
import com.zuel.item.service.ItemService;

/*
 * @author:汪思超
 * @class:前台的商品服务控制器实现
 * @date:2021.1.5
 * */

@Controller
public class ItemControllerImpl  implements ItemController{

	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = "/item/param/{sku}.html", produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public String getItemParam(@PathVariable("sku") Long sku){
        String result = itemService.getItemParamBySKU(sku);
        return result;
    }


    @GetMapping(value = "/item/desc/{sku}.html", produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public String getItemDesc(@PathVariable("sku") Long sku){
        String result = this.itemService.getItemDescBySKU(sku);
        return result;
    }

    @GetMapping("/item/{sku}.html")
    public String showItem(@PathVariable("sku") Long sku, Model model){
        ZuelItemShowObject item = this.itemService.getItemBySKU(sku);
        model.addAttribute("item", item);
        return "item";
    }

	

	
}
