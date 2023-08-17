package com.safvan.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safvan.beans.restapi.ApiTicket;
import com.safvan.request.TrainBookingApiRequest;
import com.safvan.service.restapi.IApiBookingService;

@RestController
@RequestMapping("/api/train-booking")
public class TrainBookingRestController {

	@Autowired
	private IApiBookingService apiBookingService;
	
	@PostMapping("/confirm")
	public ResponseEntity<?> confirmTrainTicketBooking(@RequestBody TrainBookingApiRequest trainBookingApiRequest) {
		
		//passing to service for booking
		ApiTicket apiTicket = apiBookingService.bookApiTicket(trainBookingApiRequest);
		
		
		System.out.println(trainBookingApiRequest);
		return new ResponseEntity<>(trainBookingApiRequest,HttpStatus.OK);
	}
}
