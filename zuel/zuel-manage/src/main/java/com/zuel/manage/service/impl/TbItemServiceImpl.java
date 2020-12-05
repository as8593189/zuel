package com.zuel.manage.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.ZuelPageResult;
import com.zuel.common.vo.ZuelRandomId;
import com.zuel.common.vo.ZuelResult;
import com.zuel.manage.dao.TbItemDao;
import com.zuel.manage.service.TbItemManageService;
import com.zuel.pojo.TbItem;

/*
 * @author:汪思超
 * @class:商品管理服务类的实现类
 * @data:2020.12.5
 * */


@Service
public class TbItemServiceImpl implements TbItemManageService{
	
	@Autowired
	private TbItemDao tbItemdao;

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
		tbItemdao.deleteById(id);
		return ZuelResult.ok();
	}

	@Override
	public ZuelResult underItem(Long id) throws Exception {
		Optional<TbItem> tbitemOptional=tbItemdao.findById(id);
		if (!tbitemOptional.isPresent()) {
			return ZuelResult.error();
		}
		TbItem item=tbitemOptional.get();
		item.setStatus(0);
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
		item.setStatus(1);
		tbItemdao.save(item);
		return ZuelResult.ok();
	}

	
}
