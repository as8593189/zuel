package com.zuel.manage.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zuel.pojo.TbItem;

@Repository
public interface TbItemDao extends JpaRepository<TbItem, Long>{

	/**
	 * 分页查询
	 * */
	@Query("select t from TbItem t where t.title like %?1% order by t.id")
	Page<TbItem> findAllOrSearch(String search,Pageable pageable);
}
