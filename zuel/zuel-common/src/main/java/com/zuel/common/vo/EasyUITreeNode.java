package com.zuel.common.vo;

import java.io.Serializable;

/*
 * @author:汪思超
 * @class:EasyUI的Tree组件提供的返回结果类型(作为动态查询分类)
 * @date:2020.12.6
 * */

public class EasyUITreeNode implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 树节点逐渐
	 */
	private Long id;
	
	/**
	 * 树节点名称
	 */
	private String text;
	
	/**
	 * 树节点状态
	 */
	private String state;

	public EasyUITreeNode(Long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}

	public EasyUITreeNode() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
