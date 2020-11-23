package com.zuel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zuel.pojo.TbItem;

public interface TbItemCatDao extends JpaRepository<TbItem, Long> {

}
