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

/**
 * UserController handles operations related to user or customer functionality.
 * This includes viewing available trains, searching for trains between
 * stations, booking tickets, viewing booking history, checking seat
 * availability, and more.
 * 
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ITrainService trainService;

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private ServletContext servletContext;

	/**
	 * Shows the home page for the user.
	 * 
	 * @return The view name for the user home page.
	 */
	@GetMapping(value = { "/", "/home" })
	public String showHomePage() {
		return "user/user_home";
	}

	/**
	 * Displays all available trains to the user.
	 * 
	 * @param model The model object to pass data to the view.
	 * @return The view name for displaying all trains.
	 */
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

	/**
	 * Forwards to the form for searching trains between stations or finding train
	 * fare between stations based on the requested URL.
	 * 
	 * This method handles the GET request for "/findTrainsbetweenStaionsFwd" and
	 * "/trainFairEnqueryFwd" URLs. It determines the functionality based on the
	 * requested URL and sets the appropriate form field names and button labels in
	 * the model. The form is designed to be dynamic, allowing the button names to
	 * change based on requirements.
	 * 
	 * @param model   The model object to pass data to the view.
	 * @param request The HTTP request object.
	 * @return The view name for the trains between stations form.
	 */
	@GetMapping(value = { "/findTrainsbetweenStaionsFwd", "/trainFairEnqueryFwd" })
	public String findTrainsbetweenStaionsForward(Map<String, Object> model, HttpServletRequest request) {
		System.out.println(request.getRequestURI());

		String pageHeading;
		String submitButtonValue;

		if (request.getRequestURI().equals(servletContext.getContextPath() + "/user/findTrainsbetweenStaionsFwd")) {
			pageHeading = "Search Trains Between Stations";
			submitButtonValue = "SEARCH TRAINS";
		} else {
			pageHeading = "Train Fare Enquiry";
			submitButtonValue = "SHOW FARE";
		}

		// passing page heading and button values
		model.put("pageHeading", pageHeading);
		model.put("submitButtonValue", submitButtonValue);

		return "user/trains_btwn_stations_form";
	}

	/**
	 * Retrieves the list of trains between two stations based on the provided
	 * station names and pass it to view for displaying.
	 * 
	 * This method handles the GET request for "/findTrainsbetweenStaions" URL. It
	 * calls the train service to get the list of trains between the specified
	 * stations. The page heading is set to display the stations being searched, and
	 * the trains list is passed to the view.
	 * 
	 * @param fromStation The name of the starting station.
	 * @param toStation   The name of the destination station.
	 * @param model       The model object to pass list of trains to the view.
	 * @return The view name for displaying the trains between stations.
	 */
	@GetMapping("/findTrainsbetweenStaions")
	public String findTrainsbetweenStaions(@RequestParam String fromStation, @RequestParam String toStation,
			Map<String, Object> model) {

		System.out.println("UserController.findTrainsbetweenStaions()");

		// Retrieve the list of trains between the specified stations
		List<Train> trainsList = trainService.getTrainsBetweenStations(fromStation, toStation);
		trainsList.forEach(System.out::println);

		// Set the page heading and trains list in the model to pass to view
		model.put("pageHeading",
				"Trains Between stations.." + fromStation.toUpperCase() + " & " + toStation.toUpperCase());
		model.put("trainsList", trainsList);

		return "user/view_trains";
	}

	/**
	 * Displays the pre-booking form for a specific train.
	 *
	 * this handler method retrieves the train details selected entered by user
	 * through form , This train details are stored in a TrainDTO object and added
	 * to the model. This user entered details are then used in view to display
	 * train_pre_booking_form.
	 *
	 * @param trainNo     The train number for which the user want to book and this
	 *                    will be diplayed in train_pre_booking_form.
	 * @param fromStation The name of the from station.
	 * @param toStation   The name of the to station.
	 * @param model       The model object to pass data to the view.
	 * @return The view name for the pre-booking form.
	 */
	@GetMapping("/showPreBookingFormForTrain")
	public String showPreBookingFormForTrain(@RequestParam Long trainNo, @RequestParam String fromStation,
			@RequestParam String toStation, Map<String, Object> model) {

		System.out.println("UserController.showPreBookingFormForTrain()+ " + trainNo);

		// Create a TrainDTO object and set the train number,
		// from station, and to station
		TrainDTO trainDTO = new TrainDTO();
		trainDTO.setTrainNo(trainNo);
		trainDTO.setFromStation(fromStation);
		trainDTO.setToStation(toStation);

		// Store the TrainDTO object in the model
		model.put("preBookingDetails", trainDTO);

		return "user/train_pre_booking_form";
	}

	/**
	 * Proceeds with the train booking for the user.
	 *
	 * It receives the TicketDTO object containing the booking details trainNo,from
	 * and to station ,seatType,berthPreference etc... as request parameters. A
	 * TrainDTO object is created and populated with the train number, from station,
	 * and to station. The TicketDTO and TrainDTO objects are added to the model.
	 * The view "user/payment_inputs_form" is returned to display the payment inputs
	 * form.
	 *
	 * @param ticketDTO   The TicketDTO object containing the booking details
	 *                    including seatType,berthPreference etc...
	 * @param trainNo     The train number.
	 * @param fromStation The from station.
	 * @param toStation   The to station.
	 * @param model       The model object to pass data to the view.
	 * @return The view name for the payment inputs form.
	 */
	@PostMapping("/proceedTrainBooking")
	public String proceedTrainBookingForUser(@ModelAttribute TicketDTO ticketDTO, @RequestParam Long trainNo,
			@RequestParam String fromStation, @RequestParam String toStation, Map<String, Object> model) {

		// Create a TrainDTO object and set the train number, from station, and to
		// station
		TrainDTO trainDTO = new TrainDTO();
		trainDTO.setTrainNo(trainNo);
		trainDTO.setFromStation(fromStation);
		trainDTO.setToStation(toStation);

		System.out.println("UserController.proceedTrainBookingForUser()");
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

		model.put("pageHeading", "Ticket Booking History");
		model.put("ticketsList", ticketsList);

		return "user/view_all_tickets";
	}

	@GetMapping(value = { "/trainSeatsAvailablityCheckFwd", "/searchTrainByNumberFwd" })
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

	@GetMapping("/searchTrainByNumber")
	public String searchTrainByNumber(@RequestParam Long trainNo, Map<String, Object> model) {

		Train train = trainService.getTrainByNumber(trainNo);

		model.put("train", train);

		return "user/display_train_details";
	}
}
