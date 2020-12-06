package com.zuel.common.vo;

import java.util.Random;

/*
 * @author:汪思超
 * @class:随机生成一个主键（随机数）
 * @date:2020.12.6
 * */
public class ZuelIdUtil {

	public static Long getId() {
		Long timeBase=System.currentTimeMillis();
		String tmp=new Random().nextInt(999)+"";
		if (tmp.length()==1) {
			tmp+="00";
		}
		tmp=tmp+timeBase;
		return Long.parseLong(tmp);
	}
}
