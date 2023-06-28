package com.safvan.service;

import java.util.List;

import com.safvan.beans.Ticket;
import com.safvan.exception.BookingFailedException;

public interface IBookingService {

	public Ticket bookTicket(Ticket ticket) throws BookingFailedException;
	
	public List<Ticket> getAllTickets();
}
