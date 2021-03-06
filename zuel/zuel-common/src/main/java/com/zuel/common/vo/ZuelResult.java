package com.zuel.common.vo;

import java.io.Serializable;

import lombok.Setter;

/*
 * @author:汪思超
 * @class:作为程序执行的返回结果，是程序间通信的标准类型
 * @date:2020.11.23
 * */
@Setter
public class ZuelResult implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param:status状态码，200为成功，500为失败
	 */
	private int status;

	/**
	 * @param:data  附带保存的数据
	 */
	private Object data;
	
	/**
	 * @param:msg  消息
	 */
	private String msg;
	
	/**
	 * 带参构造器
	 */
	public ZuelResult(int status) {
		super();
		this.status = status;
	}

	/**
	 * 不带参构造器
	 */
	public ZuelResult() {
		super();
	}
	
	/**
	 * @param:status状态码
	 * @method:成功返回方法
	 */
	public static ZuelResult ok() {
		return new ZuelResult(200);
	}
	
	public static ZuelResult ok(Object data) {
		return new ZuelResult(200,data);
	}
	
	public static ZuelResult ok(Object data,String msg) {
		return new ZuelResult(200,data,msg);
	}
	
	/**
	 * @param:status状态码
	 * @method:失败的返回方法
	 */
	public static ZuelResult error() {
		return new ZuelResult(500);
	}

	public static ZuelResult error(Object data) {
		return new ZuelResult(500,data);
	}
	
	public static ZuelResult error(Object data,String msg) {
		return new ZuelResult(500,data,msg);
	}
	
	/**
	 * 得到当前状态码
	 */
	public int getStatus() {
		return status;
	}

	public ZuelResult(int status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	public ZuelResult(int status, Object data, String msg) {
		super();
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	
	
	
}
