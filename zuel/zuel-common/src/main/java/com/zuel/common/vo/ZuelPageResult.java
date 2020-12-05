package com.zuel.common.vo;

import java.util.List;

/*
 * @author:汪思超
 * @class:作为程序的分页结果
 * @date:2020.12.5
 * */

public class ZuelPageResult<T> {

	/*
	 * 当前页面
	 * */
	private int page;
	
	/*
	 * 当前页面大小
	 * */
	private int pageSize;
	
	/*
	 * 总个数
	 * */
	private int totalNum;
	
	/*
	 * 封装的数据
	 * */
	private List<T> data;

	public ZuelPageResult(int page, int pageSize, int totalNum, List<T> data) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.data = data;
	}


	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
