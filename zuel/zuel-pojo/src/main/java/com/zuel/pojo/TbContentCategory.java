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
 *   @Pojo:目录
 *   @data:2020.11.22
 * 
 * */
@Entity
@Table(name="tb_content_category")
@Getter
@Setter
@ToString
public class TbContentCategory implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="parent_id")
    private Long parentId;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private Integer status;

    @Column(name="sort_order")
    private Integer sortOrder;

    @Column(name="is_parent")
    private Boolean isParent;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;



    
}