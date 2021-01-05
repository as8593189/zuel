package com.zuel.item.control;

import org.springframework.ui.Model;


public interface ItemController {

	public String getItemParam(Long sku);
	
	public String getItemDesc(Long sku);
	
	public String showItem(Long sku, Model model);
}
