package com.zuel.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 * @author:汪思超
 * @class:后台服务的启动类
 * @data:2020.11.23
 * */

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ManageApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ManageApp.class, args);
	}
}
