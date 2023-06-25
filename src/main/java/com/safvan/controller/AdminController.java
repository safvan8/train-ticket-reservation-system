package com.safvan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safvan.beans.TrainBean;
import com.safvan.service.ITrainService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ITrainService trainService;
	
	@GetMapping("/")
	public String showHomePage() {
		return "admin/AdminHome";
	}

	@GetMapping("/viewalltrains")
	public String viewAllTrainsForward(Map<String, Object> model) {
		
		System.out.println("AdminController.viewAllTrainsForward()");
		
		List<TrainBean> allTrains = trainService.getAllTrains();
		
		allTrains.forEach(System.out::println);
		
		model.put("allTrains", allTrains);
		
		allTrains.forEach(System.out::println);
		
		return "train/viewTrains";
	}
}
