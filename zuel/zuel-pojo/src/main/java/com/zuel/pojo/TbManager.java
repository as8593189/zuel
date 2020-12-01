package com.zuel.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 	 @author:汪思超
 *   @Pojo:后台管理员实体
 *   @data:2020.11.26
 * 
 * */



@Entity
@Table(name="tb_manager")
public class TbManager implements Serializable {

	/*
	 * 序列化
	 * 
	 * */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	@Column(name="username")
	private String username;
	
	public String getUserName() {
		return username;
	}
	
	public void setUserName(String userName) {
		this.username=userName;
	}
	
	@Column(name="password")
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
}

