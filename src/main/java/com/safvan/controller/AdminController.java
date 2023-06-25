package com.safvan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safvan.beans.TrainBean;
import com.safvan.service.ITrainService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ITrainService trainService;

	@GetMapping(value = { "/", "/home" })
	public String showHomePage() {
		return "admin/admin_home";
	}

	// to disply all available trains to admin
	@GetMapping("/viewAllTrains")
	public String viewAllTrainsForward(Map<String, Object> model) {

		System.out.println("AdminController.viewAllTrainsForward()");

		List<TrainBean> allTrains = trainService.getAllTrains();

		allTrains.forEach(System.out::println);

		model.put("allTrains", allTrains);

		allTrains.forEach(System.out::println);

		return "train/view_trains";
	}

	// Admin seatch train page forwadring method
	@GetMapping("/searchTrainByNumberFwd")
	public String searchTrainByNumberForward() {
		return "admin/search_train_form";
	}

	// perfroming real sreach operaion for admin here
	@PostMapping("/searchTrainByNumber")
	public String searchTrainByNumber(@RequestParam Long trainNo, Map<String, Object> model) {
		System.out.println("AdminController.searchTrainByNumber()");
		TrainBean train = trainService.getTrainByNumber(trainNo);

		model.put("train", train);

		return "admin/search_train_result";
	}

	// add new train forwarding method
	@GetMapping("/addTrainFwd")
	public String addTrainForward() {
		return "admin/add_train_form";
	}

	// adding a new Train by perfroming insert
	// also can be used to edit existing train details based on Id
	@PostMapping("/saveTrain")
	public String addTrain(@ModelAttribute TrainBean trainBean, Map<String, Object> model) {
		System.out.println("AdminController.addTrain()");

		String message = trainService.addTrain(trainBean);

		model.put("message", message);

		return "admin/display_message";
	}

	// delete and existing train, forwarding methods
	@GetMapping("/deleteTrainFwd")
	public String deleteTrainForward() {
		return "admin/delete_train_form";
	}

	// delete an existing train using db
	@PostMapping("/deleteTrainByNumber")
	public String deleteTrain(@RequestParam Long trainNo, Map<String, Object> model) {

		System.out.println("AdminController.deleteTrain()");
		String message = trainService.deleteTrain(trainNo);

		model.put("message", message);

		return "admin/display_message";
	}

	// to update Train
	// update train forward
	@GetMapping("/updateTrainFwd")
	public String updateTrainFwd() {
		return "admin/update_train_page";
	}

	@PostMapping("/updateTrainByNumber")
	public String showEditForm(@RequestParam Long trainNo, Map<String, Object> model) {

		System.out.println("AdminController.showEditForm()");
		TrainBean train = trainService.getTrainByNumber(trainNo);

		model.put("train", train);	
		return "admin/edit_train_details_form";
	}
}
