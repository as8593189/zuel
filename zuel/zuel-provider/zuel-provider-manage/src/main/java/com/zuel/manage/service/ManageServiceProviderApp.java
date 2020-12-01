package com.zuel.manage.service;



import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @author:汪思超
 * @app:provider启动类
 * @date:2020.11.28
 * */

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages="com.zuel.mapper")
public class ManageServiceProviderApp {
	public static void main(String[] args) {
		SpringApplication.run(ManageServiceProviderApp.class, args);
	}
}
