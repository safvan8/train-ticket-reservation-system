package com.safvan.service;

import com.safvan.beans.Ticket;
import com.safvan.exception.BookingFailedException;

public interface IBookingService {

	public Ticket bookTicket(Ticket ticket) throws BookingFailedException;
}
