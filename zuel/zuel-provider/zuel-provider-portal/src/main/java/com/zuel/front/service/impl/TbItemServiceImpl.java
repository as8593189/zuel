package com.zuel.front.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuel.common.vo.SearchItem;
import com.zuel.common.vo.ZuelItemShowObject;
import com.zuel.front.service.TbItemServiceApi;
import com.zuel.mapper.TbItemCatMapper;
import com.zuel.mapper.TbItemDescMapper;
import com.zuel.mapper.TbItemMapper;
import com.zuel.mapper.TbItemParamItemMapper;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemCat;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemParamItem;
import com.zuel.pojo.TbItemParamItemExample;

/*
 * @author:汪思超
 * @class:前台商品服务的提供者
 * @date:2020.12.22
 * */
@DubboService
public class TbItemServiceImpl implements TbItemServiceApi {

	@Autowired
	private TbItemMapper mapper;
	
	@Autowired
    private TbItemCatMapper tbItemCatMapper;
	
	
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    
    
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    
	
	@Override
	public List<SearchItem> getItemsByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return mapper.selectByIds(ids);
	}

	@Override
	public TbItem getItemBySKU(Long sku) {
		// TODO Auto-generated method stub
		TbItem item = mapper.selectByPrimaryKey(sku);
        if(null == item) return null; // 如果查询无结果，直接返回。
        ZuelItemShowObject result = new ZuelItemShowObject();
        BeanUtils.copyProperties(item, result);
        LinkedList<TbItemCat> itemCats = new LinkedList<>();
        itemCats = getItemCatsById(result.getCid(), itemCats);
        result.setItemCats(itemCats);
        return result;
	}

	private LinkedList<TbItemCat> getItemCatsById(Long id, final LinkedList<TbItemCat> itemCats){
		TbItemCat itemCat = this.tbItemCatMapper.selectByPrimaryKey(id);
        itemCats.addFirst(itemCat);
        if(itemCat.getParentId().equals(0L)){
        }else{
            return getItemCatsById(itemCat.getParentId(), itemCats);
        }
        return itemCats;
    }
	
	@Override
	public TbItemDesc getItemDescBySKU(Long sku) {
		return tbItemDescMapper.selectByPrimaryKey(sku);
	}

	@Override
	public TbItemParamItem getItemParamBySKU(Long sku) {
		// TODO Auto-generated method stub
		TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(sku);
        // 查询商品规格
        List<TbItemParamItem> tbItemParamItemList =
                this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(tbItemParamItemList.size() == 1){ 
            return tbItemParamItemList.get(0);
        }
        return null;
	}

}
