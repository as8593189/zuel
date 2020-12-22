package com.zuel.common.vo;

import java.io.Serializable;
import java.util.Objects;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

/*
 * @author:汪思超
 * @class:搜索系统中搜索的内容，关联solr字段
 * @date:2020.12.21
 * */

public class SearchItem implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Field("id")
    private Long id;
	
	
    @Field("ego_item_image")
    private String image;
    
    
    @Field("ego_item_title")
    private String title;
    
    
    @Field("ego_item_sell_point")
    private String sellPoint;
    
    
    @Field("ego_item_desc")
    private String desc;
    
    @Field("ego_item_category_name")
    private String itemCatName;
    
    @Field("ego_item_price")
    private Long price;


	public SearchItem() {
		super();
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchItem that = (SearchItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(image, that.image) &&
                Objects.equals(title, that.title) &&
                Objects.equals(sellPoint, that.sellPoint) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(itemCatName, that.itemCatName) &&
                Objects.equals(price, that.price);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(id, image, title, sellPoint, desc, itemCatName, price);
    }
	
	public String[] getImages(){
        return image.split(",");
    }
	
	public void setImages(String[] images){
        StringBuilder builder = new StringBuilder("");
        for(String tmp : images){
            builder.append(tmp).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        this.image = builder.toString();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
