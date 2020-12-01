package com.zuel.mapper;

import com.zuel.pojo.TbManager;
import com.zuel.pojo.TbManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbManagerMapper {
    long countByExample(TbManagerExample example);

    int deleteByExample(TbManagerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbManager record);

    int insertSelective(TbManager record);

    List<TbManager> selectByExample(TbManagerExample example);

    TbManager selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbManager record, @Param("example") TbManagerExample example);

    int updateByExample(@Param("record") TbManager record, @Param("example") TbManagerExample example);

    int updateByPrimaryKeySelective(TbManager record);

    int updateByPrimaryKey(TbManager record);
}