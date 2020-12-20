package com.zuel.message;

import java.io.Serializable;
import java.util.Objects;

/*
 * @author:汪思超
 * @class:将后台服务转入到前台服务，中间使用rabbitMQ过渡。本类用来写入前台缓存，实现双写一致。
 * @date:2020.12.19
 * */

public class ContentMessage implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 后台内容管理ID
	 */
	private Long contentCategoryId;
	
	/**
	 * 缓存key
	 */
    private String cacheKey;

    public ContentMessage() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentMessage that = (ContentMessage) o;
        return Objects.equals(contentCategoryId, that.contentCategoryId) &&
                Objects.equals(cacheKey, that.cacheKey);
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(contentCategoryId, cacheKey);
    }

	public Long getContentCategoryId() {
		return contentCategoryId;
	}

	public void setContentCategoryId(Long contentCategoryId) {
		this.contentCategoryId = contentCategoryId;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
