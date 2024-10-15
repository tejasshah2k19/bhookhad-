package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.AreaBean;
import com.bean.CityBean;
import com.bean.LocationBean;
import com.dao.AreaDao;
import com.dao.CityDao;
import com.dao.LocationDao;

@Controller
public class LocationController
{
	@Autowired
	CityDao citydao;
	
	@Autowired
	AreaDao areadao;
	
	@Autowired
	LocationDao locationdao;
	
	@GetMapping("/addlocation")
	public String insertlocation(Model model)
	{
		List<CityBean>cities=citydao.getAllCity();
		model.addAttribute("cities",cities);
		List<AreaBean>area=areadao.getAllArea();
		model.addAttribute("area",area);
		System.out.println(cities.size());
		return"addlocation";
	}
	
	@PostMapping("/savelocation")
	public String savelocation(LocationBean locationbean)
	{
		locationbean.setActive(true);
		locationdao.insertLocation(locationbean);
		return"home";
	}
	
	@GetMapping("/listlocation")
	public String listlocation(Model model)
	{
		List<LocationBean>locations=locationdao.getAllLocation();
		model.addAttribute("locations",locations);
		return"listlocation";
	}

}
