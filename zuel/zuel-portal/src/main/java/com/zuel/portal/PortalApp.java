package com.zuel.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/*
 * 
 * @author:汪思超
 * @app:前台系统启动器
 * @date:2020.12.19
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
public class PortalApp {

	public static void main(String[] args) {
		SpringApplication.run(PortalApp.class, args);
	}
}
