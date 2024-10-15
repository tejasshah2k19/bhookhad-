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

	public void insertoffer(OfferBean offer) {
		jdbctemplate.update(
				"insert into offers(title,description,active,startDate,endDate,locationId,foodType,offerPicPath)values(?,?,?,?,?,?,?,?)",
				offer.getTitle(), offer.getDescription(), offer.getActive(), offer.getStartDate(), offer.getEndDate(),
				offer.getLocationId(), offer.getFoodType(), offer.getOfferPicPath());
	}

	public List<OfferBean> getAllOffer() {

		List<OfferBean> offer = jdbctemplate.query(" select offers.*,location.title locationTitle from offers inner join location using(locationId);",
				new BeanPropertyRowMapper<>(OfferBean.class));
		return offer;
	}

	public List<OfferBean> getAllLatestOffer() {

		List<OfferBean> offer = jdbctemplate.query(" select offers.*,location.title locationTitle from offers inner join location using(locationId) where offers.active = 1 and offers.endDate > now() order by startDate desc;",
				new BeanPropertyRowMapper<>(OfferBean.class));
		return offer;
	}

}