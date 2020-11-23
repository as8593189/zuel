package com.zuel.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 	 @author:汪思超
 *   @Pojo:商品详情
 *   @data:2020.11.22
 * 
 * */
@Entity
@Table(name="tb_item_param")
@Getter
@Setter
@ToString
public class TbItemParam implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	 
	@Id
	@Column(name="id")
    private Long id;

	@Column(name="item_cat_id")
    private Long itemCatId;

	@Column(name="created")
    private Date created;

	@Column(name="updated")
    private Date updated;

	@Column(name="param_data")
    private String paramData;

   


}