package com.safvan.service.restapi;

import com.safvan.beans.restapi.ApiTicket;
import com.safvan.exception.booking.BookingFailedException;
import com.safvan.exception.booking.NoEnoughSeatsForBooking;
import com.safvan.request.TrainBookingApiRequest;

public interface IApiBookingService {
	
	public ApiTicket bookApiTicket(TrainBookingApiRequest trainBookingApiRequest) throws NoEnoughSeatsForBooking, BookingFailedException;
}
