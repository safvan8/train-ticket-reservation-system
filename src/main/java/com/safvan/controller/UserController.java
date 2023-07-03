package com.safvan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safvan.beans.Ticket;
import com.safvan.beans.TicketDTO;
import com.safvan.beans.Train;
import com.safvan.beans.TrainDTO;
import com.safvan.service.IBookingService;
import com.safvan.service.ITrainService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ITrainService trainService;

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private ServletContext servletContext;

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
	@GetMapping(value = { "/findTrainsbetweenStaionsFwd", "/trainFairEnqueryFwd" })
	public String findTrainsbetweenStaionsForward(Map<String, Object> model, HttpServletRequest request) {

		System.out.println(request.getRequestURI());

		if (request.getRequestURI().equals(servletContext.getContextPath() + "/user/findTrainsbetweenStaionsFwd")) {
			model.put("pageHeading", "Search trains Between stations");
			model.put("submitButtonValue", "SEARCH TRAINS");
		} else {
			model.put("pageHeading", "Trains Fair Enquery");
			model.put("submitButtonValue", "SHOW FARE");
		}

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

		System.out.println("UserController.showPreBookingFormForTrain()+ " + trainNo);

		// get all train details using number for saving inside ticket
		TrainDTO trainDTO = new TrainDTO();
		// setting train from and to with user preferences
		trainDTO.setTrainNo(trainNo);
		trainDTO.setFromStation(fromStation);
		trainDTO.setToStation(toStation);

		// saving object to request scope
		model.put("preBookingDetails", trainDTO);

		return "user/train_pre_booking_form";
	}

	@PostMapping("/proceedTrainBooking")
	public String procedTrainBookingForUser(@ModelAttribute TicketDTO ticketDTO, @RequestParam Long trainNo,
			@RequestParam String fromStation, @RequestParam String toStation, Map<String, Object> model) {

		TrainDTO trainDTO = new TrainDTO();
		trainDTO.setTrainNo(trainNo);
		trainDTO.setFromStation(fromStation);
		trainDTO.setToStation(toStation);

		System.out.println("UserController.procedTrainBookingForUser()");
		System.out.println(trainDTO);
		System.out.println(ticketDTO);

		model.put("ticketDTO", ticketDTO);
		model.put("trainDTO", trainDTO);

		return "user/payment_inputs_form";
	}

	@PostMapping("/confirmTrainBooking")
	public String confirmTrainBooking(@ModelAttribute("trainDTO") TrainDTO trainDTO,
			@ModelAttribute("ticketDTO") TicketDTO ticketDTO, Map<String, Object> model) {
		System.out.println("UserController.confirmTrainBooking().................");
		System.out.println(ticketDTO);
		System.out.println(trainDTO);

		// to copy property values from one object to another based on matching property
		// names.
		Train train = new Train();
		BeanUtils.copyProperties(trainDTO, train);

		Ticket ticket = new Ticket();
		BeanUtils.copyProperties(ticketDTO, ticket);

		// saving train details to ticket object
		ticket.setTrain(train);

		System.out.println("Fina....");
		System.out.println(ticket);

		// booking ticket
		Ticket ticketBookingResult = bookingService.bookTicket(ticket);

		model.put("ticketBookingResult", ticketBookingResult);

		return "user/ticket_booking_result";
	}


	@GetMapping("/showTicketBookingHistory")
	public String getAllTicketsBooked(Map<String, Object> model) {

		List<Ticket> ticketsList = bookingService.getAllTickets();

		model.put("pageHeading","Ticket Booking History");
		model.put("ticketsList", ticketsList);
		

		return "user/view_all_tickets";
	}

		
		@GetMapping(value = {"/trainSeatsAvailablityCheckFwd", "/searchTrainByNumberFwd"})
		public String showTrainNumberinputForm(HttpServletRequest request, Map<String, Object> model) {

		    if (request.getRequestURI().equals("/user/trainSeatsAvailablityCheckFwd")) {
		        model.put("pageHeading", "Train Seats Availability Check !");
		        model.put("submitButtonValue", "CHECK SEATS AVAILABLE");
		    } else {
		        model.put("pageHeading", "Search Trains!");
		        model.put("submitButtonValue", "SEARCH TRAIN");
		    }

		    return "user/train_number_input_form";
		}

	@PostMapping("/searchTrainByNumber")
	public String searchTrainByNumber(@RequestParam Long trainNo, Map<String, Object> model) {

		Train train = trainService.getTrainByNumber(trainNo);

		model.put("train", train);

		return "user/display_train_details";
	}
}
