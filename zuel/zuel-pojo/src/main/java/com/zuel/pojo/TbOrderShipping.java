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
 *   @Pojo:订单物流
 *   @data:2020.11.22
 * 
 * */
@Entity
@Table(name="tb_order_shipping")
@Getter
@Setter
@ToString
public class TbOrderShipping implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="order_id")
    private String orderId;

    @Column(name="receiver_name")
    private String receiverName;

    @Column(name="receiver_phone")
    private String receiverPhone;

    @Column(name="receiver_mobile")
    private String receiverMobile;

    @Column(name="receiver_state")
    private String receiverState;

    @Column(name="receiver_city")
    private String receiverCity;

    @Column(name="receiver_district")
    private String receiverDistrict;

    @Column(name="receiver_address")
    private String receiverAddress;

    @Column(name="receiver_zip")
    private String receiverZip;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;



}