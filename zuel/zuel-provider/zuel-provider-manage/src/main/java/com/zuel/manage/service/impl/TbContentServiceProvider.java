package com.zuel.manage.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zuel.common.vo.EasyUIDatagrid;
import com.zuel.exception.ServiceException;
import com.zuel.manage.service.TbContentServiceApi;
import com.zuel.mapper.TbContentMapper;
import com.zuel.pojo.TbContent;
import com.zuel.pojo.TbContentExample;

/*
 * 
 * @author:汪思超
 * @service:后台系统内容管理相关服务
 * @date:2020.12.14
 * */
@DubboService
public class TbContentServiceProvider implements TbContentServiceApi {

	@Autowired
    private TbContentMapper tbContentMapper;
	
	@Override
	public EasyUIDatagrid<TbContent> getContentByCategory(Long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
        EasyUIDatagrid<TbContent> result = new EasyUIDatagrid<>();
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setRows(pageInfo.getList());
		return result;
	}

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean saveContent(TbContent content) throws ServiceException {
		// TODO Auto-generated method stub
		 if(null != content.getCreated()){ 
	            int rows = this.tbContentMapper.insertSelective(content);
	            if(rows == 1){
	                return true;
	            }
	        }else{
	            int rows = this.tbContentMapper.updateByPrimaryKeySelective(content);
	            if(rows == 1){
	                return true;
	            }
	        }
	        throw new ServiceException("保存内容发生异常");
	}

	@Override
	@Transactional(rollbackFor = {ServiceException.class})
	public boolean removeContent(Long[] ids) throws ServiceException {
		try{
            TbContentExample example = new TbContentExample();
            List<Long> params = Arrays.asList(ids);
            example.createCriteria().andIdIn(params);
            int rows = this.tbContentMapper.deleteByExample(example);
            if (rows == ids.length) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new ServiceException("删除内容发生异常");
	}

}
