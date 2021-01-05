package com.zuel.common.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemCat;

/*
 * @author:汪思超
 * @class:展示商品的详情需要使用的对象
 * @date:2021.1.5
 * */

public class ZuelItemShowObject extends TbItem{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 要保存的商品分类
	 */
	private List<TbItemCat> itemCats;
	
	
    public void setImages(String[] images){ }
    
  
    public String[] getImages(){
        if(null == this.getImage() || "".equals(this.getImage())){
            return new String[]{};
        }
        return this.getImage().split(",");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZuelItemShowObject that = (ZuelItemShowObject) o;
        return Objects.equals(itemCats, that.itemCats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCats);
    }

    public String getCreatedStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(this.getCreated());
    }
    public void setCreatedStr(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(str);
            this.setCreated(date);
        } catch (ParseException e) {
            e.printStackTrace(); 
        }
    }

    public List<TbItemCat> getItemCats() {
        return itemCats;
    }

    public void setItemCats(List<TbItemCat> itemCats) {
        this.itemCats = itemCats;
    }
	
}
