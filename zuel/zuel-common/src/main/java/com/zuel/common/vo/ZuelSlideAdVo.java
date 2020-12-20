package com.zuel.common.vo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;

/*
 * @author:汪思超
 * @class:显示轮播广告信息的值对象
 * @date:2020.12.19
 * */

public class ZuelSlideAdVo implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 图片地址 pic字段
	 * */
	private String src; 

	/*
	 * 图片宽度
	 * */
    private String width; 
    
    /*
	 * 图片高度
	 * */
    private String height; 
    
    /*
	 * 图片地址 pic2字段
	 * */
    private String srcB; 
    
    /*
	 * 图片宽度b
	 * */
    private String widthB; 
    
    /*
	 * 图片高度b
	 * */
    private String heightB; 
    
    /*
	 * 广告地址
	 * */
    private String href; 
    
    /*
   	 * 图片描述
   	 * */
    private String alt;

    
    
    public ZuelSlideAdVo() {
		super();
	}

	public static class Builder{
        private final Properties properties;
        public Builder(){
            properties = new Properties();
            try {
                properties.load(Builder.class.getClassLoader()
                        .getResourceAsStream("cms-ad.properties"));
            }catch(Exception e){
                e.printStackTrace();
                properties.clear();
                properties.setProperty("zuel.portal.slide.ad.width", "670");
                properties.setProperty("zuel.portal.slide.ad.height", "240");
                properties.setProperty("zuel.portal.slide.ad.widthB", "550");
                properties.setProperty("zuel.portal.slide.ad.heightB", "240");
            }
        }
        public ZuelSlideAdVo build(){
        	ZuelSlideAdVo zuelSlideAD = new ZuelSlideAdVo();
        	zuelSlideAD.setWidth(properties.getProperty("zuel.portal.slide.ad.width"));
        	zuelSlideAD.setHeight(properties.getProperty("zuel.portal.slide.ad.height"));
        	zuelSlideAD.setWidthB(properties.getProperty("zuel.portal.slide.ad.widthB"));
        	zuelSlideAD.setHeightB(properties.getProperty("zuel.portal.slide.ad.heightB"));
            return zuelSlideAD;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZuelSlideAdVo that = (ZuelSlideAdVo) o;
        return Objects.equals(src, that.src) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height) &&
                Objects.equals(srcB, that.srcB) &&
                Objects.equals(widthB, that.widthB) &&
                Objects.equals(heightB, that.heightB) &&
                Objects.equals(href, that.href) &&
                Objects.equals(alt, that.alt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, width, height, srcB, widthB, heightB, href, alt);
    }

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSrcB() {
		return srcB;
	}

	public void setSrcB(String srcB) {
		this.srcB = srcB;
	}

	public String getWidthB() {
		return widthB;
	}

	public void setWidthB(String widthB) {
		this.widthB = widthB;
	}

	public String getHeightB() {
		return heightB;
	}

	public void setHeightB(String heightB) {
		this.heightB = heightB;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

    
}
