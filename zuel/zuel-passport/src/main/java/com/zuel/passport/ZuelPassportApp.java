package com.zuel.passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/*
 * @author:汪思超
 * @class:用户登录服务启动器
 * @date:2021.1.6
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
public class ZuelPassportApp {

	
	public static void main(String[] args) {
		SpringApplication.run(ZuelPassportApp.class, args);
	}
}
