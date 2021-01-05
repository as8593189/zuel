package com.zuel.item.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zuel.common.vo.ZuelItemShowObject;
import com.zuel.common.vo.ZuelJason;
import com.zuel.front.service.TbItemServiceApi;
import com.zuel.item.service.ItemService;
import com.zuel.pojo.TbItem;
import com.zuel.pojo.TbItemDesc;
import com.zuel.pojo.TbItemParamItem;

/*
 * @author:汪思超
 * @class:前台的商品服务实现类
 * @date:2021.1.5
 * */

@Service
@CacheConfig(cacheNames = "zuel:front:item")
public class ItemServiceImpl implements ItemService {

	@DubboReference
    private TbItemServiceApi tbItemServiceAPI;

    /**
     * 根据商品主键，查询规格数据。
     * @param sku 商品主键
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @Cacheable(key = "'getItemParamBySKU('+#sku+')'")
    public String getItemParamBySKU(Long sku) {
        TbItemParamItem itemParamItem = this.tbItemServiceAPI.getItemParamBySKU(sku);
        if(itemParamItem == null){ 
            return "<span style='width: 90%; padding: 10px; text-align: center'>服务器忙，请稍后重试！</span>";
        }
        String paramData = itemParamItem.getParamData();
        List<Map> paramList = ZuelJason.str2List(paramData, Map.class);
        if(paramList == null){
            return "<span style='width: 90%; padding: 10px; text-align: center'>服务器忙，请稍后重试！</span>";
        }
        StringBuilder builder = new StringBuilder("");
        for(Map paramObj : paramList){ 
            builder.append("<table style='width: 90%; padding: 10px' border='1'>");
            builder.append("<tr>");
            builder.append("<td colspan='2' style='text-align: center; width: 100%'>");
            builder.append(paramObj.get("group"));
            builder.append("</td>");
            builder.append("</tr>");
            List<Map> paramsList = (List<Map>) paramObj.get("params");
            for(Map params : paramsList){ 
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

        return builder.toString();
    }

    /**
     * 根据商品的主键，查询商品详情数据。
     * @param sku 商品主键
     * @return
     */
    @Override
    @Cacheable(key = "'getItemDescBySKU('+#sku+')'")
    public String getItemDescBySKU(Long sku) {
        TbItemDesc itemDesc = this.tbItemServiceAPI.getItemDescBySKU(sku);
        if(itemDesc == null){
            return "";
        }
        return itemDesc.getItemDesc();
    }

    /**
     * 根据商品主键，查询商品数据。
     * @param sku 商品主键
     * @return
     */
    @Override
    @Cacheable(key = "'getItemBySKU('+#sku+')'")
    public ZuelItemShowObject getItemBySKU(Long sku) {
        TbItem item = this.tbItemServiceAPI.getItemBySKU(sku);
        if(null == item){ 
            return new ZuelItemShowObject();
        }
        ZuelItemShowObject result = new ZuelItemShowObject();
        BeanUtils.copyProperties(item, result);
        return result;
    }
}
