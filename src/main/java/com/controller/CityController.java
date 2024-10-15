package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.CityBean;
import com.dao.CityDao;

@Controller
public class CityController 
{
	@Autowired
	CityDao citydao;
	
	
	@GetMapping("/addcity")
	public String insertcity()
	{
		return"addcity";
	}
	
	@PostMapping("/savecity")
	public String savecity(CityBean citybean)
	{
		citydao.inserCity(citybean);
		return"home";
	}
	
	@GetMapping("/listcity")
	public String listcity(Model model)
	{	
		List<CityBean>cities=citydao.getAllCity();
		model.addAttribute("cities",cities);
		return"listcity";
	}
	
	
}


