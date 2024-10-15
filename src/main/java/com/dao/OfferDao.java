package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.LocationBean;
import com.bean.OfferBean;

@Repository
public class OfferDao {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	public void insertoffer(OfferBean offer)
	{
		jdbctemplate.update("insert into offer(title,description,active,startDate,endDate,locationId,foodType)values(?,?,?,?,?,?,?)",
				offer.getTitle(),offer.getDescription(),offer.getActive(),offer.getStartDate(),
				offer.getEndDate(),offer.getLocationId(),offer.getFoodType());
	}

	public List<OfferBean> getAllOffer() {
		
		List<OfferBean>offer=jdbctemplate.query(" select * from offer inner join location using(locationId);",new BeanPropertyRowMapper<>(OfferBean.class) );
		return offer;
	}
	
	
}