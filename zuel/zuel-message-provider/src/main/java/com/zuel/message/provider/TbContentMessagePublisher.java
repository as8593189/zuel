package com.zuel.message.provider;

import org.springframework.amqp.core.AmqpTemplate;

import com.zuel.message.ContentMessage;

/*
 * @author:汪思超
 * @class:后台内容管理的消息发送者
 * @date:2020.12.20
 * */



public class TbContentMessagePublisher {

	private AmqpTemplate ampTemplate;
	
	public void sendMessage(String exchange, String routingKey, ContentMessage message){
        ampTemplate.convertAndSend(exchange, routingKey, message);
    }

	public AmqpTemplate getAmpTemplate() {
		return ampTemplate;
	}

	public void setAmpTemplate(AmqpTemplate ampTemplate) {
		this.ampTemplate = ampTemplate;
	}
	
	
}
