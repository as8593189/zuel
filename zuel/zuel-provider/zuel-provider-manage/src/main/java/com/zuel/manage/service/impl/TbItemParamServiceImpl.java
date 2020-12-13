package com.zuel.manage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.common.vo.TbItemParamSubPage;
import com.zuel.common.vo.ZuelIdUtil;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbItemParamServiceApi;
import com.zuel.mapper.TbItemCatMapper;
import com.zuel.mapper.TbItemParamMapper;
import com.zuel.pojo.TbItemCat;
import com.zuel.pojo.TbItemCatExample;
import com.zuel.pojo.TbItemParam;
import com.zuel.pojo.TbItemParamExample;

/*
 * @author:汪思超
 * @class:商品类型规格服务实现
 * @data:2020.12.13
 * */
@DubboService
public class TbItemParamServiceImpl implements TbItemParamServiceApi {

	@Autowired
    private TbItemParamMapper tbItemParamMapper;
	
	
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public EasyUIDatagrid<TbItemParam> getTbItemParamByPage(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
        List<TbItemParam> tbItemParamList = tbItemParamMapper.selectByExampleWithBLOBs(null);
        List<Long> itemCatIdList = new ArrayList<Long>();
        for(TbItemParam tbItemParam : tbItemParamList){ 
            itemCatIdList.add(tbItemParam.getItemCatId());
        }
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andIdIn(itemCatIdList);
        List<TbItemCat> itemCatList = tbItemCatMapper.selectByExample(example);
        Map<Long, String> itemCatNameMap = new HashMap<>();
        for(TbItemCat itemCat : itemCatList){
            itemCatNameMap.put(itemCat.getId(), itemCat.getName());
        }
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(tbItemParamList);
        EasyUIDatagrid<TbItemParam> result = new EasyUIDatagrid<>();
        result.setTotal(pageInfo.getTotal());
        List<TbItemParam> dataList = new ArrayList<>();
        for(TbItemParam itemParam : tbItemParamList){
            TbItemParamSubPage sub = new TbItemParamSubPage();
            BeanUtils.copyProperties(itemParam, sub);
            sub.setItemCatName(itemCatNameMap.get(sub.getItemCatId()));
            dataList.add(sub);
        }
        // 返回的数据集合，是List<TbItemParamSub>
        result.setRows(dataList);
        return result;
	}

	@Override
	public TbItemParam getTbItemParamByItemCatId(Long itemCatId) {
		// TODO Auto-generated method stub
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(itemCatId);
		List<TbItemParam> params = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (params.size()==1) {
			return params.get(0);
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean saveTbItemParam(TbItemParam itemParam) throws ServiceException {
		try {
			//如果是新增数据
            if (itemParam.getId() == null) {
                Long id = ZuelIdUtil.getId();
                itemParam.setId(id);
                int rows = this.tbItemParamMapper.insertSelective(itemParam);
                if (rows == 1) { 
                    return true;
                }
            } else {
                //如果是更新数据
                int rows = this.tbItemParamMapper.updateByPrimaryKeySelective(itemParam);
                if (rows == 1) { 
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        throw new ServiceException("保存商品规格异常");
	}

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean deleteTbItemParamsByIds(Long[] ids) throws ServiceException {
		try {
            TbItemParamExample example = new TbItemParamExample();
            List<Long> idList = Arrays.asList(ids);
            example.createCriteria().andIdIn(idList);
            int rows = this.tbItemParamMapper.deleteByExample(example);
            if (rows == ids.length) {
                return true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        throw new ServiceException("删除规格失败");
	}

	
}
