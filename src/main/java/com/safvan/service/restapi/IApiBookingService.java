package com.safvan.service.restapi;

import com.safvan.beans.Ticket;
import com.safvan.exception.booking.BookingFailedException;
import com.safvan.exception.booking.NoEnoughSeatsForBooking;

public interface IApiBookingService {
	
	public Ticket bookApiTicket(Ticket ticket) throws NoEnoughSeatsForBooking, BookingFailedException;
}
