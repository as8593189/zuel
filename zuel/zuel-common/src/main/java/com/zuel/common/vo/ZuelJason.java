package com.zuel.common.vo;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @author:汪思超
 * @class:作为程序的java对象与Jason对象互换（java转Jason）
 * @date:2020.12.19
 * */

public class ZuelJason {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	
	public static <T> List<T> str2List(String jsonStr, Class<T> beanType){
        // 创建jackson中的类型描述对象。
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        List<T> list = null;
        try {
            // 转换json格式字符串到java集合类型对象。
            list = MAPPER.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; 
        }
        return list;
    }
	
	public static <T> T str2Obj(String jsonStr, Class<T> clazz){
        T t = null;
        try {
            // 转换json格式字符串到Java类型对象。
            t = MAPPER.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // 转换异常，返回null
        }
        return t;
    }
	
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
