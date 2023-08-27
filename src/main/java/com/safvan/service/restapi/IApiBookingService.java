package com.safvan.service.restapi;

import com.safvan.beans.restapi.ApiTicket;
import com.safvan.exception.mvc.booking.BookingFailedException;
import com.safvan.exception.mvc.booking.NoEnoughSeatsForBooking;
import com.safvan.request.TrainBookingApiRequest;

public interface IApiBookingService {
	
	public ApiTicket bookApiTicket(TrainBookingApiRequest trainBookingApiRequest) throws NoEnoughSeatsForBooking, BookingFailedException;
}
