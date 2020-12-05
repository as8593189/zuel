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
 *   @Pojo:商品
 *   @data:2020.11.22
 * 
 * */
@Entity
@Table(name="tb_item")
@Getter
@Setter
@ToString
public class TbItem implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="sell_point")
    private String sellPoint;

    @Column(name="price")
    private Long price;

    @Column(name="num")
    private Integer num;

    @Column(name="barcode")
    private String barcode;

    @Column(name="image")
    private String image;

    @Column(name="cid")
    private Long cid;

    @Column(name="status")
    private int status;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;




}