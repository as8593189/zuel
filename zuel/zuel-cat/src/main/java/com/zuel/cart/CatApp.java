package com.zuel.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/*
 * @author:汪思超
 * @class:购物车系统启动类
 * @date:2021.1.9
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
public class CatApp {

	public static void main(String[] args) {
		SpringApplication.run(CatApp.class, args);
	}
}
