package com.zuel.front.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 
 * @author:汪思超
 * @app:前台系统服务提供启动类
 * @date:2020.12.18
 * */
@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.zuel.mapper")
public class FrontServiceProviderApp {

	public static void main(String[] args) {
		SpringApplication.run(FrontServiceProviderApp.class, args);
	}
}
