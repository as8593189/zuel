package com.zuel.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 	 @author:汪思超
 *   @Pojo:订单商品
 *   @data:2020.11.22
 * 
 * */
@Entity
@Table(name="tb_order_item")
@Getter
@Setter
@ToString
public class TbOrderItem implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name="id")
    private String id;

	@Column(name="item_id")
    private String itemId;

	@Column(name="order_id")
    private String orderId;

	@Column(name="num")
    private Integer num;

	@Column(name="title")
    private String title;

	@Column(name="price")
    private Long price;

	@Column(name="total_fee")
    private Long totalFee;

	@Column(name="pic_path")
    private String picPath;




}