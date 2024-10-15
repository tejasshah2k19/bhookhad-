package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.AreaBean;
import com.bean.UserBean;

@Repository
public class AreaDao 
{
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public void addArea(AreaBean areabean) {
	
		jdbctemplate.update("insert into area(areaName,areaId,cityId) values(?,?,?)",areabean.getAreaName(),areabean.getAreaId(),areabean.getCityId());
	}
	public List<AreaBean> getAllArea()
	{
		List<AreaBean> area = jdbctemplate.query("select * from area inner join city using (cityId);", new BeanPropertyRowMapper<>(AreaBean.class));
		return area;
	}

  
	
}
