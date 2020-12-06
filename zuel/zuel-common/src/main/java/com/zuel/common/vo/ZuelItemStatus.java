package com.zuel.common.vo;

/*
 * @author:汪思超
 * @class:作为商品状态码的常量集
 * @date:2020.12.6
 * */

public class ZuelItemStatus {

	/*
	 * 商品有误
	 * */
	public static byte errorItem=0;
	
	/*
	 * 商品下架
	 * */
	public static byte downItem=2;
	
	
	/*
	 * 商品上架
	 * */
	public static byte UpItem=1;
	
	/*
	 * 商品被删除
	 * */
	public static byte DeteleItem=3;
	
}
