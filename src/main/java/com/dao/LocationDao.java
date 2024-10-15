package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.LocationBean;

@Repository
public class LocationDao 
{
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public void insertLocation(LocationBean location)
	{
		jdbctemplate.update("insert into location(title,category,description,timings,active,contactNumber,address,cityId,areaId,latitude,longitude,foodType) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?)",
				location.getTitle(),location.getCategory(),location.getDescription(),location.getTimings(),
				location.isActive(),location.getContactNumber(),location.getAddress(),location.getCityId(),
				location.getAreaId(),location.getLatitude(),location.getLongitude(),location.getFoodType());
	}
        public List<LocationBean> getAllLocation() {
		
		List<LocationBean>location=jdbctemplate.query(" select * from location inner join city using(cityId) inner join area using(areaId);",new BeanPropertyRowMapper<>(LocationBean.class) );
		return location;
	}
	
	

}
