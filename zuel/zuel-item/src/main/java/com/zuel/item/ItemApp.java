package com.zuel.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/*
 * 
 * @author:汪思超
 * @app:商品系统启动器
 * @date:2020.12.18
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@EnableCaching
public class ItemApp {
	public static void main(String[] args) {
		SpringApplication.run(ItemApp.class, args);
	}
}
