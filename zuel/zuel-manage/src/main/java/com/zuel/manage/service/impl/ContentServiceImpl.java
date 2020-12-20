package com.zuel.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelIdUtil;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.ContentService;
import com.zuel.manage.service.TbContentServiceApi;
import com.zuel.message.ContentMessage;
import com.zuel.message.provider.TbContentMessagePublisher;
import com.zuel.pojo.TbContent;

/*
 * 
 * @author:汪思超
 * @service:内容管理服务实现类
 * @date:2020.12.14
 * */
@Service
public class ContentServiceImpl implements ContentService {
	
	@DubboReference
	private TbContentServiceApi service;

	@Autowired
    private TbContentMessagePublisher publisher;
	
	@Value("${zuel.message.content.sync.exchange}")
    private String exchange;
	
	
    @Value("${zuel.message.content.sync.routingKey}")
    private String routingKey;
	
	@Override
	public EasyUIDatagrid<TbContent> getContentByCategory(Long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		if(categoryId.equals(0L)) { 
            EasyUIDatagrid<TbContent> result = new EasyUIDatagrid<>();
            result.setTotal(0L);
            result.setRows(new ArrayList<>());
            return result;
        }else{
            return service.getContentByCategory(categoryId, page, rows);
        }
	}

	@Override
	public ZuelResult saveContent(TbContent content) throws ServiceException {
		// TODO Auto-generated method stub
		try {
            Date now = new Date();
            if (null == content.getId()) {
                content.setId(ZuelIdUtil.getId());
                content.setCreated(now);
                content.setUpdated(now);
            } else {
                content.setUpdated(now);
            }
            boolean isSaved = service.saveContent(content);
            if (isSaved) {
                ContentMessage message = new ContentMessage();
                message.setContentCategoryId(content.getCategoryId());
                message.setCacheKey("getSlideAd()");
                publisher.sendMessage(exchange, routingKey, message);
                return ZuelResult.ok();
            }
        }catch (ServiceException e){
            e.printStackTrace();
            throw e; 
        }
        return ZuelResult.error();
	}

	@Override
	public ZuelResult removeContent(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		try {
            TbContent content = service.getContentById(ids[0]);
            boolean isDeleted = service.removeContent(ids);
            if(isDeleted){
                // 发送消息到MQ，通知消息的消费者，同步缓存。
                ContentMessage message = new ContentMessage();
                message.setContentCategoryId(content.getCategoryId());
                message.setCacheKey("getSlideAd()");
                publisher.sendMessage(exchange, routingKey, message);
                return ZuelResult.ok();
            }
        }catch (ServiceException e){
            e.printStackTrace();
            throw e;
        }
        return ZuelResult.error();
	}
}
