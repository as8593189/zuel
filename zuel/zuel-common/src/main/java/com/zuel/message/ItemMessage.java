package com.zuel.message;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/*
 * @author:汪思超
 * @class:商品消息，同步DB和solr
 * @date:2020.12.21
 * */

public class ItemMessage extends ZuelMessage implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 主键
	 * */
	 private Long[] ids;
	 
	 /*
	  * delete表示要删除solr
	  * update表示要查询DB，保存Solr
	  * */
	 private String flag;
	
	 public ItemMessage() {
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ItemMessage that = (ItemMessage) o;
	        return Arrays.equals(ids, that.ids) &&
	                Objects.equals(flag, that.flag);
	    }

	    @Override
	    public int hashCode() {
	        int result = Objects.hash(flag);
	        result = 31 * result + Arrays.hashCode(ids);
	        return result;
	    }
	    
	    public void addId(Long... ids){
	        if(this.ids == null){
	        	//没有初始化必须先返回
	            this.ids = ids;
	            return;
	        }
	        // 初始化之后加上新值
	        Long[] newArray = new Long[this.ids.length + ids.length];
	        for(int i = 0; i < this.ids.length; i++){
	            newArray[i] = this.ids[i];
	        }
	        for(int i = 0; i < ids.length; i++){
	            newArray[this.ids.length + i] = ids[i];
	        }
	        this.ids = newArray;
	    }

		public Long[] getIds() {
			return ids;
		}

		public void setIds(Long[] ids) {
			this.ids = ids;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	    
	    
}
