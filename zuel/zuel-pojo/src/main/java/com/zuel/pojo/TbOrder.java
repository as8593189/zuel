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
 *   @Pojo:订单
 *   @data:2020.11.22
 * 
 * */
@Entity
@Table(name="tb_order")
@Getter
@Setter
@ToString
public class TbOrder implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="order_id")
    private String orderId;

    @Column(name="payment")
    private String payment;

    @Column(name="payment_type")
    private Integer paymentType;

    @Column(name="post_fee")
    private String postFee;

    @Column(name="status")
    private Integer status;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="update_time")
    private Date updateTime;

    @Column(name="payment_time")
    private Date paymentTime;

    @Column(name="consign_time")
    private Date consignTime;

    @Column(name="end_time")
    private Date endTime;

    @Column(name="close_time")
    private Date closeTime;

    @Column(name="shipping_name")
    private String shippingName;

    @Column(name="shipping_code")
    private String shippingCode;

    @Column(name="user_id")
    private Long userId;

    @Column(name="buyer_message")
    private String buyerMessage;

    @Column(name="buyer_nick")
    private String buyerNick;

    @Column(name="buyer_rate")
    private Integer buyerRate;



}