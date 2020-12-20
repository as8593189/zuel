package com.zuel.message.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/*
 * @author:汪思超
 * @class:消息队列接收者启动类
 * @date:2020.12.20
 * */

@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
public class MessageConsumerApp {

	public static void main(String[] args) {
		SpringApplication.run(MessageConsumerApp.class, args);
	}
}
