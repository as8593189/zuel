package com.zuel.message.provider;

import org.springframework.amqp.core.AmqpTemplate;

import com.zuel.message.ZuelMessage;

/*
 * @author:汪思超
 * @class:后台内容管理的消息发送者
 * @date:2020.12.20
 * */



public class TbMessagePublisher {

	private AmqpTemplate template;

 
    public void sendMessage(String exchange, String routingKey, ZuelMessage message){

        template.convertAndSend(exchange, routingKey, message);

    }


	public AmqpTemplate getTemplate() {
		return template;
	}


	public void setTemplate(AmqpTemplate template) {
		this.template = template;
	}


	
	
}
