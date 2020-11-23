package com.zuel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zuel.pojo.TbOrder;

public interface TbOrderDao extends JpaRepository<TbOrder, String> {

}
