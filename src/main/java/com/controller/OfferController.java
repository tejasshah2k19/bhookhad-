package com.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.LocationBean;
import com.bean.OfferBean;
import com.dao.LocationDao;
import com.dao.OfferDao;

@Controller
public class OfferController {

	@Autowired
	LocationDao locationdao;

	@Autowired
	OfferDao offerdao;

	@GetMapping("/addoffer")
	public String addoffer(Model model) {

		List<LocationBean> location = locationdao.getAllLocation();
		model.addAttribute("location", location);
		return "addoffer";
	}

	@PostMapping("saveoffer")
	public String saveoffer(OfferBean offer) {
		offer.setActive(true);

		try {
			File file = new File("C:\\projects\\Bhookad\\Bhookad\\src\\main\\webapp\\offerpic",
					offer.getOfferPic().getOriginalFilename());
			
			FileUtils.writeByteArrayToFile(file, offer.getOfferPic().getBytes());
			
			offer.setOfferPicPath("offerpic/"+offer.getOfferPic().getOriginalFilename());
			offerdao.insertoffer(offer);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "home";
	}

	@GetMapping("/listoffer")
	public String listoffer(Model model) {
		List<OfferBean> listoffer = offerdao.getAllOffer();
		model.addAttribute("listoffer", listoffer);
		model.addAttribute("today",LocalDate.now());
		return "listoffer";
	}
}