package com.zuel.common.vo;

import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author:汪思超
 * @class:cookie工具类
 * @date:2021.1.9
 * */

public class ZuelCookie {

	public static final boolean setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int maxAge){
        if(null == cookieName || null == cookieValue){ 
            return false;
        }
        Cookie cookie = new Cookie(cookieName, Base64.getEncoder().encodeToString(cookieValue.getBytes()));
        if(maxAge > 0){
            cookie.setMaxAge(maxAge);
        }
        String domain = "";
        String requestURL = request.getRequestURL().toString(); 
        if(null == requestURL || "".equals(requestURL)){
            domain = "";
        }else{
            if(requestURL.startsWith("http://")){
                requestURL = requestURL.substring(7);
            }
            if(requestURL.startsWith("https://")){ 
                requestURL = requestURL.substring(8);
            }
            requestURL = requestURL.substring(0, requestURL.indexOf("/"));
            String[] tmp = requestURL.split("\\.");
            if(tmp.length > 3){ 
                domain = "." + tmp[tmp.length - 3] + "." + tmp[tmp.length - 2] + "." + tmp[tmp.length - 1];
            }else if(tmp.length <= 3 && tmp.length >= 2){
                domain = "." + tmp[tmp.length - 2] + "." + tmp[tmp.length - 1];
            }else{
                domain = tmp[0]; 
            }
        }
        if(domain.indexOf(":") > 0){ 
            domain = domain.substring(0, domain.indexOf(":"));
        }
        cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie); 
        return true;
    }

    /**
     * 根据cookie的name，查询cookie的value
     * @return 当cookieName没有对应值的时候，返回null
     */
    public static final String getCookieValue(HttpServletRequest request, String cookieName){
        String result = null;
        Cookie[] cookies = request.getCookies();
        if(null == cookies || cookies.length == 0 || null == cookieName){
            return null;
        }
        for(Cookie cookie : cookies){
            if(cookieName.equals(cookie.getName())){
                result = new String(Base64.getDecoder().decode(cookie.getValue()));
                return result;
            }
        }
        return null;
    }
}
