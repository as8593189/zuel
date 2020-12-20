package com.zuel.common.vo;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @author:汪思超
 * @class:作为程序的java对象与Jason对象互换（java转Jason）
 * @date:2020.12.19
 * */

public class ZuelJason {

	private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String object2Json(Object object){
        String result = "";
        try{
            result = MAPPER.writeValueAsString(object);
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        return result;
    }
}
