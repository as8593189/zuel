package com.zuel.passport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

/*
 * @author:汪思超
 * @class:用户登录系统的相关配置
 * @date:2021.1.6
 * */
@Configuration
public class PassportConfiguration {

	@Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("TT_TOKEN");
        cookieSerializer.setUseHttpOnlyCookie(false);
        return cookieSerializer;
    }


    @Bean
    public HttpSessionIdResolver httpSessionIdResolver(CookieSerializer cookieSerializer){
        CookieHttpSessionIdResolver httpSessionIdResolver = new CookieHttpSessionIdResolver();
        httpSessionIdResolver.setCookieSerializer(cookieSerializer);
        return httpSessionIdResolver;
    }
	
	
}
