package com.zuel.common.vo;

import java.util.Random;

/*
 * @author:汪思超
 * @class:获得一个随机ID
 * @date:2020.12.5
 * */

public class ZuelRandomId {

	private  Long randomId;
	
	public ZuelRandomId() {
		randomId=(long) (new Random().nextInt(500000)+1000);
	}

	public Long getRandomId() {
		return randomId;
	}

	public void setRandomId(Long randomId) {
		this.randomId =  randomId;
	}
	
	
}
