package com.safvan.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.Ticket;
import com.safvan.beans.Train;
import com.safvan.exception.booking.BookingFailedException;
import com.safvan.exception.booking.NoEnoughSeatsForBooking;
import com.safvan.repository.ITicketRepository;
import com.safvan.service.IBookingService;
import com.safvan.service.ITrainService;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private ITicketRepository ticketRepository;

	@Autowired
	private ITrainService trainService;

	@Override

	@Transactional
	public Ticket bookTicket(Ticket ticket) {

		// fetching complete train details and saving to ticket before booking
		Train train = trainService.getTrainByNumber(ticket.getTrain().getTrainNo());

		train.setFromStation(ticket.getTrain().getFromStation());
		train.setToStation(ticket.getTrain().getToStation());

		Integer seatsVaialble = train.getSeats();

		Ticket ticketBookigResult = null;

		if (ticket.getSeatsRequired() > seatsVaialble) {

			throw new NoEnoughSeatsForBooking("Only " + seatsVaialble + " are Availble in this Train ! ");

		} else if (ticket.getSeatsRequired() <= seatsVaialble) {
			seatsVaialble = seatsVaialble - ticket.getSeatsRequired();

			// updating vailble seats with new number
			train.setSeats(seatsVaialble);

			try {
				// updating train
				trainService.saveOrUpdateTrain(train);

				String transactionId = UUID.randomUUID().toString();
				Double fare = train.getFare();
				Integer seatsRequired = ticket.getSeatsRequired();
				Double totalAmount = fare * seatsRequired;

				// ticket details updating
				ticket.setTransactionId(transactionId);
				ticket.setTicketAmount(totalAmount);

				// creating ticket and confirmation
				ticketBookigResult = ticketRepository.save(ticket);

				// adding trains complete infor to ticketBookingResult object for displying
				ticketBookigResult.setTrain(train);

				System.out.println("BookingServiceImpl.bookTicket()=============================");
				System.out.println(ticket);

			} catch (Exception e) {
				throw new BookingFailedException("Booking failed for the train number: " + train.getTrainNo());
			}

		}

		System.out.println("BookingServiceImpl.bookTicket()");
		System.out.println(ticket);

		// Save the ticket to the database
		return ticketBookigResult;
	}

	@Override
	public List<Ticket> getAllTickets() {
		
		return (List<Ticket>) ticketRepository.findAll();
	}

}
