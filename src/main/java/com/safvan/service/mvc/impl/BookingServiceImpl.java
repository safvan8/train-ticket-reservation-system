package com.safvan.service.mvc.impl;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.Ticket;
import com.safvan.beans.Train;
import com.safvan.beans.User;
import com.safvan.constants.TicketStatus;
import com.safvan.exception.mvc.booking.BookingFailedException;
import com.safvan.exception.mvc.booking.NoEnoughSeatsForBooking;
import com.safvan.exception.restapi.booking.ApiBookingFailedException;
import com.safvan.exception.restapi.booking.ApiNoEnoughSeatsForBooking;
import com.safvan.repository.mvc.ITicketRepository;
import com.safvan.service.mvc.IBookingService;
import com.safvan.service.mvc.ITrainService;

/**
 * The BookingServiceImpl class is responsible for handling booking-related
 * operations.
 *
 * It implements the IBookingService interface and provides methods for booking
 * tickets and retrieving all tickets.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private ITicketRepository ticketRepository;

	@Autowired
	private ITrainService trainService;

	/**
	 * Books a ticket based on the provided ticket details.
	 *
	 * @param ticket The Ticket object containing the details of the ticket to be
	 *               booked.
	 * @return The Ticket object representing the booked ticket.
	 * @throws ApiNoEnoughSeatsForBooking If there are not enough seats available on
	 *                                    the train for booking.
	 * @throws ApiBookingFailedException  If an error occurs while booking the
	 *                                    ticket.
	 */
	@Override
	@Transactional
	public Ticket bookTicket(Ticket ticket) {

		// fetching complete train details and saving to ticket before booking
		Train train = trainService.getTrainByNumber(ticket.getTrain().getTrainNo());

		train.setFromStation(ticket.getTrain().getFromStation());
		train.setToStation(ticket.getTrain().getToStation());

		Integer seatsAvailable = train.getSeats();

		Ticket ticketBookingResult = null;

		// predicate checks if the required seats are not available.
		Predicate<Ticket> areSeatsUnavailable =  
				requestedTicket  -> requestedTicket.getSeatsRequired() > train.getSeats();
		
				
		if (areSeatsUnavailable.test(ticket)) {
			String userFriendlyMessage = "Only " + seatsAvailable + " seats are available on this train!";
			throw new NoEnoughSeatsForBooking(Thread.currentThread().getStackTrace(), userFriendlyMessage);
		} else {
			seatsAvailable = seatsAvailable - ticket.getSeatsRequired();

			// updating available seats with the new number
			train.setSeats(seatsAvailable);

			try {
				String transactionId = UUID.randomUUID().toString();
				Double fare = train.getFare();
				Integer seatsRequired = ticket.getSeatsRequired();
				Double totalAmount = fare * seatsRequired;

				// ticket details updating
				ticket.setTransactionId(transactionId);
				ticket.setTicketStatus(TicketStatus.BOOKED);
				ticket.setTicketAmount(totalAmount);

				// creating ticket and confirmation
				ticketBookingResult = ticketRepository.save(ticket);
				// setting complete train detailas to display after booking ticket
				trainService.saveOrUpdateTrain(train);

				// adding train's complete information to ticketBookingResult object for
				// displaying
				ticketBookingResult.setTrain(train);

			} catch (Exception e) {
				e.printStackTrace();
				String userFriendlyMessage = "Booking failed for the train number: " + train.getTrainNo();
				throw new BookingFailedException(Thread.currentThread().getStackTrace(), userFriendlyMessage);
			}

		}
		return ticketBookingResult;
	}

	/**
	 * Retrieves a list of all tickets from the database.
	 *
	 * @return A list of Ticket objects representing all tickets in the system.
	 */
	@Override
	public List<Ticket> getAllTickets() {
		return (List<Ticket>) ticketRepository.findAll();
	}

	/**
	 * Retrieves all tickets booked by a specific user.
	 * 
	 * @param user the user object representing the customer.
	 * @return a List of tickets booked by the user.
	 */
	@Override
	public List<Ticket> getTicketsByUser(User user) {
		return ticketRepository.findByUser(user);
	}

}
