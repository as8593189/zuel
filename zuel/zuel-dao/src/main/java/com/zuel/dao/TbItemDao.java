package com.zuel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zuel.pojo.TbItem;

public interface TbItemDao extends JpaRepository<TbItem, Long>{

}
