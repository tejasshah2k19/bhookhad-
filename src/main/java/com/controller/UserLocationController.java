package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dao.LocationDao;
import com.dao.OfferDao;

@Controller
public class UserLocationController {

	@Autowired
	LocationDao locationDao;
	
	@Autowired
	OfferDao offerDao;
	
	@GetMapping("/")
	public String home(Model model) {
	
		model.addAttribute("offers",offerDao.getAllLatestOffer());
		return "UserHome";
	}
	
}
