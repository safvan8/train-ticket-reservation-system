package com.safvan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safvan.beans.Train;
import com.safvan.service.ITrainService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ITrainService trainService;

	@GetMapping(value = { "/", "/home" })
	public String showHomePage() {
		return "user/user_home";
	}

	// to disply all available trains to user
	@GetMapping("/viewAllTrains")
	public String viewAllTrainsForward(Map<String, Object> model) {

		System.out.println("UserController.viewAllTrainsForward()");

		List<Train> allTrains = trainService.getAllTrains();

		allTrains.forEach(System.out::println);

		model.put("allTrains", allTrains);

		allTrains.forEach(System.out::println);

		return "user/view_trains";
	}
	
	@GetMapping("/findTrainsbetweenStaionsFwd")
	public String findTrainsbetweenStaionsForward()
	{
		return "user/trains_btwn_staions_form";
	}
}
