package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.dao.LocationDao;
import com.dao.OfferDao;
import com.bean.LocationBean;
import com.bean.OfferBean;

@Controller
public class OfferController {

	@Autowired
	LocationDao locationdao;
	
	@Autowired
	OfferDao offerdao;
	
	@GetMapping("/addother")
	public String addoffer(Model model)
	{
		List<LocationBean>location=locationdao.getAllLocation();
		model.addAttribute("location",location);
		return"addoffer";
	}
	
	@PostMapping("saveoffer")
	public String saveoffer(OfferBean offer)
	{
		System.out.println(offer.getLocationId());
		offerdao.insertoffer(offer);
		return"home";
	}
	
	@GetMapping("/listoffer")
	public String listoffer(Model model)
	{
		List<OfferBean>listoffer=offerdao.getAllOffer();
		model.addAttribute("listoffer",listoffer);
		return"listoffer";
	}
}