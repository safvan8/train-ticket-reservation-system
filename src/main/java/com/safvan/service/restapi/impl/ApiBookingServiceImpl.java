package com.safvan.service.restapi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.Train;
import com.safvan.beans.restapi.ApiTicket;
import com.safvan.exception.booking.NoEnoughSeatsForBooking;
import com.safvan.request.TrainBookingApiRequest;
import com.safvan.service.ITrainService;
import com.safvan.service.restapi.IApiBookingService;

@Service
public class ApiBookingServiceImpl implements IApiBookingService {

	@Autowired
	private ITrainService trainService;

	@Override
	public ApiTicket bookApiTicket(TrainBookingApiRequest trainBookingApiRequest) {

		// fetching complete train details and saving to ticket before booking
		// train starting, setdination, fare all details presernt the object
		Train trainForBooking = trainService.getTrainByNumber(trainBookingApiRequest.getTrainNo());

		Integer seatsAvailable = trainForBooking.getSeats();

		if (seatsAvailable < trainBookingApiRequest.getSeatsRequired()) {
			String userFriendlyMessage = "Only " + seatsAvailable + " seats are available on this train!";
			throw new NoEnoughSeatsForBooking(Thread.currentThread().getStackTrace(), userFriendlyMessage);
		}

		return null;
	}

}
