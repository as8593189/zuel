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
	
	/**
	 * @param:status状态码
	 * @method:失败的返回方法
	 */
	public static ZuelResult error() {
		return new ZuelResult(500);
	}

	/**
	 * 得到当前状态码
	 */
	public int getStatus() {
		return status;
	}

	
	
	
}
