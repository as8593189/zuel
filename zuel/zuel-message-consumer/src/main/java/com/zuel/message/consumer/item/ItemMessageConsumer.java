package com.zuel.message.consumer.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zuel.common.vo.SearchItem;
import com.zuel.common.vo.ZuelItemShowObject;
import com.zuel.common.vo.ZuelJason;
import com.zuel.front.service.TbItemServiceApi;
import com.zuel.message.ItemMessage;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemParamItem;
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
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${zuel.cache.front.item.itemBased}")
    private String cacheKeyItemBased;
    
    @Value("${zuel.cache.front.item.itemDesc}")
    private String cacheKeyItemDesc;
    
    @Value("${zuel.cache.front.item.itemParam}")
    private String cacheKeyItemParam;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "${zuel.message.item.sync.queueName}", autoDelete = "false"),
                    exchange = @Exchange(name = "${zuel.message.item.sync.exchange}", type = "direct"),
                    key = "${zuel.message.item.sync.routingKey}"
            )
    })
    public void syncItem(ItemMessage itemMessage){
        if("delete".equals(itemMessage.getFlag())){
            List<String> ids = new ArrayList<>();
            for(Long id : itemMessage.getIds()){
                ids.add(id.toString());
            }
            Collection<String> frontCacheKeys = new ArrayList<>();
            for(Long id : itemMessage.getIds()){ 
                frontCacheKeys.add(this.cacheKeyItemBased + "(" + id + ")");
                frontCacheKeys.add(this.cacheKeyItemDesc + "(" + id + ")");
                frontCacheKeys.add(this.cacheKeyItemParam + "(" + id + ")");
            }
            redisTemplate.delete(frontCacheKeys);
            boolean isDeleted = solrDao.delete(ids);
            int index = 0;
            while(!isDeleted){
                index++;
                if(index >= 10){
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isDeleted = solrDao.delete(ids);
            }
        }
        if("update".equals(itemMessage.getFlag())){
            for(Long id : itemMessage.getIds()) {
                TbItem tbItem = service.getItemBySKU(id);
                TbItemDesc itemDesc = service.getItemDescBySKU(id);
                TbItemParamItem itemParamItem = service.getItemParamBySKU(id);
                ZuelItemShowObject itemShowObject = new ZuelItemShowObject();
                if (tbItem != null) {
                    BeanUtils.copyProperties(tbItem, itemShowObject);
                }
                String itemDescCache = "";
                if (itemDesc != null) {
                    itemDescCache = itemDesc.getItemDesc();
                }
                String itemParamItemCache = "";
                if (itemParamItem == null) { 
                    itemParamItemCache = "<span style='width: 90%; padding: 10px; text-align: center'>服务器忙，请稍后重试！</span>";
                }
                else {
                    String paramData = itemParamItem.getParamData();
                    List<Map> paramList = ZuelJason.str2List(paramData, Map.class);
                    if (paramList == null) {
                        itemParamItemCache = "<span style='width: 90%; padding: 10px; text-align: center'>服务器忙，请稍后重试！</span>";
                    }
                    else {
                        StringBuilder builder = new StringBuilder("");
                        for (Map paramObj : paramList) { 
                            builder.append("<table style='width: 90%; padding: 10px' border='1'>"); 
                            builder.append("<tr>");
                            builder.append("<td colspan='2' style='text-align: center; width: 100%'>");
                            builder.append(paramObj.get("group"));
                            builder.append("</td>");
                            builder.append("</tr>");
                            List<Map> paramsList = (List<Map>) paramObj.get("params");
                            for (Map params : paramsList) {
                                builder.append("<tr>");
                                builder.append("<td style='text-align: right; padding-right: 10px; width:30%'>");
                                builder.append(params.get("k"));
                                builder.append("</td>");
                                builder.append("<td style='text-align: left; padding-left: 10px; width:70%'>");
                                builder.append(params.get("v")); 
                                builder.append("</td>");
                                builder.append("</tr>");
                            }
                            builder.append("</table>"); 
                        }
                        itemParamItemCache = builder.toString();
                    }
                }

                redisTemplate.opsForValue().set(this.cacheKeyItemBased+"("+id+")", itemShowObject);
                redisTemplate.opsForValue().set(this.cacheKeyItemDesc+"("+id+")", itemDescCache);
                redisTemplate.opsForValue().set(this.cacheKeyItemParam+"("+id+")", itemParamItemCache);
            }
            List<SearchItem> list = service.getItemsByIds(itemMessage.getIds());
            boolean isSaved = solrDao.save(list);
            int index = 0;
            while (!isSaved){
                index++;
                if(index >= 10){ 
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isSaved = solrDao.save(list);
            }
        }
    }
}
