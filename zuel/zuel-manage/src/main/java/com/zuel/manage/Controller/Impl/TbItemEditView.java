package com.zuel.manage.Controller.Impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author:汪思超
 * @class:后台商品管理修改的页面跳转，因为懒得改另一个类的返回格式，单独开一个类搞这个
 * @data:2020.12.13
 * */
@Controller
public class TbItemEditView {

	/*
	 * 做视图跳转不能用json格式
	 * */
	@RequestMapping(value="/rest/page/item-edit")
    public String toEditItemPage(){
        return "item-edit";
    }
}
