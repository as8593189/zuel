package com.zuel.common.vo;

import org.springframework.util.DigestUtils;

/*
 * @author:汪思超
 * @class:用户密码加密工具
 * @date:2021.1.6
 * */


public class ZuelUserPasswordDigest {

	
	public static String digest(String source){
        return DigestUtils.md5DigestAsHex(source.getBytes());
    }
}
