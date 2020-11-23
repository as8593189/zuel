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
 *   @Pojo:顾客
 *   @data:2020.11.22
 * 
 * */
@Entity
@Table(name="tb_user")
@Getter
@Setter
@ToString
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;


 
}