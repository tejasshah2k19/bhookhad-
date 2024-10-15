package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CityBean;

@Repository
public class CityDao 
{
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public void inserCity(CityBean city)
	{
		jdbctemplate.update("insert into city(cityName)values(?)",city.getCityName());
	}
	
	public List<CityBean> getAllCity()
	{
		List<CityBean> cities = jdbctemplate.query("select * from city order by cityId", new BeanPropertyRowMapper<>(CityBean.class));
		return cities;
	}

}
