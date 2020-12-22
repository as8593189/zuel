package com.zuel.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/*
 * @author:汪思超
 * @class:搜索系统启动类
 * @data:2020.12.21
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
public class SearchApp {

	public static void main(String[] args) {
		SpringApplication.run(SearchApp.class, args);
	}
}
