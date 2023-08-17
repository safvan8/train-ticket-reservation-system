package com.safvan.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safvan.request.TrainBookingApiRequest;

@RestController
@RequestMapping("/api/train-booking")
public class TrainBookingRestController {

	@PostMapping("/confirm")
	public ResponseEntity<?> confirmTrainTicketBooking(@RequestBody TrainBookingApiRequest trainBookingApiRequest) {
		System.out.println("================");
		System.out.println(trainBookingApiRequest);
		return new ResponseEntity<TrainBookingApiRequest>(trainBookingApiRequest,HttpStatus.OK);
	}
}
