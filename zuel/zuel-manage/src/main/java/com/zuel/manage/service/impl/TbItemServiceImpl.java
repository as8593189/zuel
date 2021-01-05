package com.zuel.manage.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.ZuelIdUtil;
import com.zuel.common.vo.ZuelItemStatus;
import com.zuel.common.vo.ZuelPageResult;
import com.zuel.common.vo.ZuelRandomId;
import com.zuel.common.vo.ZuelResult;
import com.zuel.exception.ServiceException;
import com.zuel.manage.dao.TbItemDao;
import com.zuel.manage.service.TbItemManageService;
import com.zuel.manage.service.TbItemSaveProviderService;
import com.zuel.manage.service.TbItemStatusModify;
import com.zuel.message.ItemMessage;
import com.zuel.message.provider.TbMessagePublisher;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemParamItem;

/*
 * @author:汪思超
 * @class:商品管理服务类的实现类
 * @data:2020.12.5
 * */


@Service
public class TbItemServiceImpl implements TbItemManageService{
	
	@Autowired
	private TbItemDao tbItemdao;
	
	@DubboReference
	private TbItemStatusModify statusModify;

	@DubboReference
	private TbItemSaveProviderService saveService;
	
	@Autowired
	private TbMessagePublisher publisher;
	
	@Value("${zuel.message.item.sync.exchange}")
    private String exchange;
	
    @Value("${zuel.message.item.sync.routingKey}")
    private String routingKey;

	
	@Override
	public ZuelPageResult<TbItem> getItems(int page, int size, String search) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(page, size);
		Page<TbItem> itemPage=tbItemdao.findAllOrSearch(search, pageable);
		return new ZuelPageResult<TbItem>(page, size, (int) itemPage.getTotalElements(), itemPage.getContent());
	}

	@Override
	public ZuelResult addItem(TbItem item) {
		// TODO Auto-generated method stub
		TbItem item2=new TbItem();
		item2.setBarcode(item.getBarcode());
		item2.setCid(item.getCid());
		item2.setCreated(new Date(System.currentTimeMillis()));
		item2.setUpdated(new Date(System.currentTimeMillis()));
		item2.setId(new ZuelRandomId().getRandomId());
		item2.setImage(item.getImage());
		item2.setNum(item.getNum());
		item2.setPrice(item.getPrice());
		item2.setSellPoint(item.getSellPoint());
		item2.setStatus(0);
		item2.setTitle(item.getTitle());
		tbItemdao.save(item2);
		return ZuelResult.ok();
	}

	@Override
	public ZuelResult updateItem(TbItem item) throws Exception {
		// TODO Auto-generated method stub
		Optional<TbItem> tbitemOptional=tbItemdao.findById(item.getId());
		if (!tbitemOptional.isPresent()) {
			return ZuelResult.error();
		}
		TbItem item2=tbitemOptional.get();
		item2.setBarcode(item.getBarcode());
		item2.setCid(item.getCid());
		item2.setCreated(new Date(System.currentTimeMillis()));
		item2.setUpdated(new Date(System.currentTimeMillis()));
		item2.setId(new ZuelRandomId().getRandomId());
		item2.setImage(item.getImage());
		item2.setNum(item.getNum());
		item2.setPrice(item.getPrice());
		item2.setSellPoint(item.getSellPoint());
		item2.setStatus(0);
		item2.setTitle(item.getTitle());
		tbItemdao.save(item2);
		return ZuelResult.ok();
	}

	@Override
	public ZuelResult deleteItem(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<TbItem> tbitemOptional=tbItemdao.findById(id);
		if (!tbitemOptional.isPresent()) {
			return ZuelResult.error();
		}
		//tbItemdao.deleteById(id);
		//不直接删除，变成标记删除
		tbitemOptional.get().setStatus(3);
		tbItemdao.save(tbitemOptional.get());
		return ZuelResult.ok();
	}

	@Override
	public ZuelResult underItem(Long id) throws Exception {
		Optional<TbItem> tbitemOptional=tbItemdao.findById(id);
		if (!tbitemOptional.isPresent()) {
			return ZuelResult.error();
		}
		TbItem item=tbitemOptional.get();
		item.setStatus(1);
		tbItemdao.save(item);
		return ZuelResult.ok();
	}

	@Override
	public ZuelResult upItem(Long id) {
		// TODO Auto-generated method stub
		Optional<TbItem> tbitemOptional=tbItemdao.findById(id);
		if (!tbitemOptional.isPresent()) {
			return ZuelResult.error();
		}
		TbItem item=tbitemOptional.get();
		item.setStatus(2);
		tbItemdao.save(item);
		return ZuelResult.ok();
	}

	@Override
	public EasyUIDatagrid<TbItem> getItemsByPage(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		//适配前台UI页面
		Pageable pageable=PageRequest.of(page, rows);
		Page<TbItem> itemPage=tbItemdao.findAll( pageable);
		return new EasyUIDatagrid<TbItem>(itemPage.getTotalElements(), itemPage.getContent());
	}

	@Override
	public ZuelResult deleteItemByStatus(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			boolean isModified = statusModify.modifyStatus(ids,ZuelItemStatus.DeteleItem);
			if (isModified) {
                // 发送消息，同步solr
                ItemMessage itemMessage = new ItemMessage();
                itemMessage.setFlag("delete");
                itemMessage.addId(ids);
               publisher.sendMessage(exchange, routingKey, itemMessage);
                return ZuelResult.ok();
            }
			return ZuelResult.ok();
		} catch (ServiceException e) {
			// TODO: handle exception
			e.getStackTrace();
			throw new ServiceException("后台删除商品失效");
		}
		
	}

	@Override
	public ZuelResult underItem2(Long[] ids) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			boolean isModified = statusModify.modifyStatus(ids,ZuelItemStatus.downItem);
			if (isModified) { 
                ItemMessage itemMessage = new ItemMessage();
                itemMessage.setFlag("delete");
                itemMessage.addId(ids);
                publisher.sendMessage(exchange, routingKey, itemMessage);
                return ZuelResult.ok();
            }
		} catch (ServiceException e) {
			// TODO: handle exception
			e.getStackTrace();
			throw new ServiceException("后台下架商品失效");
		}
		return ZuelResult.error();
	}

	@Override
	public ZuelResult upItem2(Long[] ids)  throws ServiceException{
		// TODO Auto-generated method stub
		try {
			boolean isModified =statusModify.modifyStatus(ids,ZuelItemStatus.UpItem);
			 if (isModified) { 
	                ItemMessage itemMessage = new ItemMessage();
	                itemMessage.setFlag("update");
	                itemMessage.addId(ids);
	                publisher.sendMessage(exchange, routingKey, itemMessage);
	                return ZuelResult.ok();
	            }
		} catch (ServiceException e) {
			// TODO: handle exception
			e.getStackTrace();
			throw new ServiceException("后台上架商品失效");
		}
		return ZuelResult.error();
	}

	@Override
	public ZuelResult saveItem(TbItem item, String desc,String itemParams, Long itemParamId) throws ServiceException {
		try {
            TbItemDesc itemDesc = new TbItemDesc();
            TbItemParamItem itemParamItem = new TbItemParamItem();
            Date now = new Date(); 
            if (null == item.getId()) {
                Long itemId = ZuelIdUtil.getId();
                item.setId(itemId); 
                itemDesc.setItemId(itemId); 
                item.setCreated(now); 
                itemDesc.setCreated(now); 
                itemParamItem.setId(ZuelIdUtil.getId()); 
                itemParamItem.setCreated(now); 
            }
            item.setUpdated(now); 
            itemDesc.setItemDesc(desc); 
            itemDesc.setUpdated(now);
            itemParamItem.setId(itemParamId == null ? itemParamItem.getId() : itemParamId); 
            itemParamItem.setParamData(itemParams); 
            itemParamItem.setUpdated(now);
            boolean isSaved = saveService.saveItem(item, itemDesc, itemParamItem);
            if(isSaved){
                ItemMessage itemMessage = new ItemMessage();
                itemMessage.setFlag("update");
                itemMessage.addId(item.getId());
                publisher.sendMessage(exchange, routingKey, itemMessage);
                return ZuelResult.ok();
            }
        }catch(ServiceException e){
            e.printStackTrace();
            throw e;
        }
        return ZuelResult.error();
    }
	

	
}
