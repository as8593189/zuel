package com.zuel.common.vo;

import java.util.Objects;

/*
 * @author:汪思超
 * @class:购物车中的商品类型
 * @date:2021.1.9
 * */

public class ZuelCartItem {

	private Long id; 
    private String title; 
    private String image;
    private Long price;
    private int num; 

    public ZuelCartItem() {
    }

    public void addNum(int num){
        this.num += num;
    }

    public String[] getImages(){
        return image.split(",");
    }
    public void setImages(String[] images){ }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZuelCartItem that = (ZuelCartItem) o;
        return num == that.num &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(image, that.image) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, image, price, num);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
