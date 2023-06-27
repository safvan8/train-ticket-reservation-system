package com.safvan.service.impl;

import org.springframework.stereotype.Service;

import com.safvan.beans.Ticket;
import com.safvan.repository.ITicketRepository;
import com.safvan.service.IBookingService;

@Service
public class BookingServiceImpl implements IBookingService {

	private ITicketRepository ticketRepository;

	@Override
	public Ticket bookTicket(Ticket ticket) {

		// Save the ticket to the database
		return ticketRepository.save(ticket);
	}

}
