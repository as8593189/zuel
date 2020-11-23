package com.zuel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zuel.pojo.TbUser;

public interface TbUserDao extends JpaRepository<TbUser, Long> {

}
