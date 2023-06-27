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

import com.safvan.beans.Ticket;
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

		List<Train> trainsList = trainService.getAllTrains();

		trainsList.forEach(System.out::println);

		// writing page heading and setting data to request scope
		model.put("pageHeading", "All Available running Trains - displaying to user..");
		model.put("trainsList", trainsList);

		return "user/view_trains";
	}

	// forwarding method
	@GetMapping("/findTrainsbetweenStaionsFwd")
	public String findTrainsbetweenStaionsForward() {
		return "user/trains_btwn_stations_form";
	}

	// fomd trains betwen 2 stations
	@PostMapping("/findTrainsbetweenStaions")
	public String findTrainsbetweenStaions(@RequestParam String fromStation, @RequestParam String toStation,
			Map<String, Object> model) {

		System.out.println("UserController.findTrainsbetweenStaions()");

		List<Train> trainsList = trainService.getTrainsBetweenStations(fromStation, toStation);
		trainsList.forEach(System.out::println);

		// writing page heading and setting data to request scope
		model.put("pageHeading",
				"Trains Between stations.." + fromStation.toUpperCase() + " & " + toStation.toUpperCase());
		model.put("trainsList", trainsList);

		return "user/view_trains";
	}

	// disply book trains page

	@GetMapping("/showPreBookingFormForTrain")
	public String showPreBookingFormForTrain(@RequestParam Long trainNo, @RequestParam String fromStation,
			@RequestParam String toStation, Map<String, Object> model) {

		// get all train details using number for saving inside ticket
		Train train = new Train();
		// setting train from and to with user preferences
		train.setFromStation(fromStation);
		train.setToStation(toStation);

		// saving object to request scope
		model.put("preBookingDetails", train);

		return "user/train_pre_booking_form";
	}

	@PostMapping("/procedTrainBooking")
	public String procedTrainBookingForUser(Long trainNo, @RequestParam String fromStation,
			@RequestParam String toStation, @ModelAttribute Ticket ticket, Map<String, Object> model) {

		// get all train details using number for saving inside ticket
		Train train = trainService.getTrainByNumber(trainNo);
		// setting train from and to with user preferences
		train.setFromStation(fromStation);
		train.setToStation(toStation);

		return "user/payment_inputs_form";
	}

}
