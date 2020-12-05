package com.zuel.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * @author:汪思超
 * @class:security配置类
 * @data:2020.12.1
 * */

@Configuration
public class ManageSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//自定义安全页面，修改配置
		http.formLogin().loginProcessingUrl("/login")
									.loginPage("/login")
									.successForwardUrl("/indexSuccess");
		//验证配置
		http.authorizeRequests()
										.antMatchers("/", "/login", "/css/**", "/js/**").permitAll()
										.anyRequest().authenticated();
		//关闭csrf
		http.csrf().disable();
	}
	
	//使用散列加密算法
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
}
