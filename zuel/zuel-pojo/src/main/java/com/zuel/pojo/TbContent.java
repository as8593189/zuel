package com.zuel.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

/*
 * 	 @author:汪思超
 *   @Pojo:内容
 *   @data:2020.11.22
 * 
 * */


@Entity
@Table(name="tb_content")
@Getter
@Setter
@ToString
public class TbContent implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="category_id")
    private Long categoryId;

    @Column(name="title")
    private String title;

    @Column(name="sub_title")
    private String subTitle;

    @Column(name="title_desc")
    private String titleDesc;

    @Column(name="url")
    private String url;

    @Column(name="pic")
    private String pic;

    @Column(name="pic2")
    private String pic2;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    @Column(name="content")
    private String content;




}