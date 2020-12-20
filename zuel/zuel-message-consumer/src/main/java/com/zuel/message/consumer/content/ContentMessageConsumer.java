package com.zuel.message.consumer.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zuel.common.vo.ZuelJason;
import com.zuel.common.vo.ZuelSlideAdVo;
import com.zuel.front.service.TbContentServiceAPI;
import com.zuel.message.ContentMessage;
import com.zuel.pojo.TbContent;

/*
 * 
 * @author:汪思超
 * @app:前台对后台内容接收的处理，用于监控队列，创建后用于读取配置文件
 * @date:2020.12.20
 * */
@Component
public class ContentMessageConsumer {

	@DubboReference
    private TbContentServiceAPI tbContentServiceAPI;
	
    @Value("${zuel.portal.ad.slide.categoryId}")
    private Long slideCategoryId;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RabbitListener(bindings = {
    		@QueueBinding(
                    value = @Queue(value = "${zuel.message.content.sync.queueName}", autoDelete = "false"),
                    exchange = @Exchange(value = "${zuel.message.content.sync.exchange}", type = "direct"),
                    key = "${zuel.message.content.sync.routingKey}"
            )
    })
    public void syncContent(ContentMessage message){
        List<TbContent> list = tbContentServiceAPI.getContentByCategory(message.getContentCategoryId());
        @SuppressWarnings("rawtypes")
		List adList = tbContent2Ad(list, message.getContentCategoryId());
        String keyPrefix = properties.getProperty("" + message.getContentCategoryId());
        redisTemplate.opsForValue().set(keyPrefix+"::"+message.getCacheKey(), ZuelJason.object2Json(adList));
    }

    @SuppressWarnings("rawtypes")
	private List tbContent2Ad(List<TbContent> list, Long categoryId){
        List<Object> result = new ArrayList<>();
        if(slideCategoryId.equals(categoryId)){
            ZuelSlideAdVo.Builder builder = new ZuelSlideAdVo.Builder();
            // 轮播图广告
            for(TbContent content : list){
                ZuelSlideAdVo item = builder.build();
                item.setSrcB(content.getPic2());
                item.setSrc(content.getPic());
                item.setHref(content.getUrl());
                item.setAlt(content.getTitle());
                result.add(item);
            }
        }else{
            // 未实现的广告。
        }
        return result;
    }

    private Properties properties;

    // 构造方法，读取配置文件content-cacheNames.properties为后续逻辑提供基础数据。
    public ContentMessageConsumer(){
        properties = new Properties();
        try {
            properties.load(ContentMessageConsumer.class.getClassLoader()
                    .getResourceAsStream("content-cacheNames.properties"));
        } catch (IOException e) {
            System.out.println("配置文件content-cacheNames.properties读取错误将启动默认值");
            properties.setProperty("89", "ego:front:portal:ad:slide");
        }
    }
}
