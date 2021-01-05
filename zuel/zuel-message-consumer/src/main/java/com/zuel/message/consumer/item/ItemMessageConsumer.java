package com.zuel.message.consumer.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zuel.common.vo.SearchItem;
import com.zuel.front.service.TbItemServiceApi;
import com.zuel.message.ItemMessage;
import com.zuel.search.dao.SolrDao;

/*
 * 
 * @author:汪思超
 * @class:进行商品同步消费者
 * @date:2020.12.22
 * */

@Component
public class ItemMessageConsumer {

	@Autowired
	private SolrDao solrDao;
	
	@DubboReference
	private TbItemServiceApi service;
	
	@RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "${zuel.message.item.sync.queueName}", autoDelete = "false"),
                    exchange = @Exchange(name = "${zuel.message.item.sync.exchange}", type = "direct"),
                    key = "${zuel.message.item.sync.routingKey}"
            )
    })
	public void syncItem(ItemMessage itemMessage){
        if("delete".equals(itemMessage.getFlag())){
        	//同步删除
            List<String> ids = new ArrayList<>();
            for(Long id : itemMessage.getIds()){
                ids.add(id.toString());
            }
            boolean isDeleted = solrDao.delete(ids);
            // 定义一个计数器
            int index = 0;
            while(!isDeleted){
                index++;
                if(index >= 10){
                    //十次以上进行跳出
                    break;
                }
                try {
                	//删除失败休眠
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isDeleted = solrDao.delete(ids);
            }
        }
        if("update".equals(itemMessage.getFlag())){
        	//同步更新
            List<SearchItem> list = service.getItemsByIds(itemMessage.getIds());
            System.out.println("保存前腰");
            boolean isSaved = solrDao.save(list);
            // 定义计数器
            int index = 0;
            while (!isSaved){
                index++;
                if(index >= 10){ 
                	//保存十次跳出
                    break;
                }
                try {
                	//保存失败休眠
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isSaved = solrDao.save(list);
            }
        }
    }
}
