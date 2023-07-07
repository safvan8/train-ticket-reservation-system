package com.safvan.service;

import java.util.List;

import com.safvan.beans.Ticket;
import com.safvan.beans.User;
import com.safvan.exception.booking.BookingFailedException;
import com.safvan.exception.booking.NoEnoughSeatsForBooking;

/**
 * The IBookingService interface provides methods for booking tickets and
 * retrieving all tickets.
 *
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
public interface IBookingService {

	/**
	 * Books a ticket based on the provided ticket details.
	 *
	 * @param ticket The Ticket object containing the details of the ticket to be
	 *               booked.
	 * @return The Ticket object representing the booked ticket.
	 * @throws NoEnoughSeatsForBooking If there are not enough seats available on
	 *                                 the train for booking.
	 * @throws BookingFailedException  If an error occurs while booking the ticket.
	 */
	public Ticket bookTicket(Ticket ticket) throws NoEnoughSeatsForBooking, BookingFailedException;

	/**
	 * Retrieves a list of all tickets from the database.
	 *
	 * @return A list of Ticket objects representing all tickets in the system.
	 */
	public List<Ticket> getAllTickets();

	/**
	 * Retrieves all tickets booked by a specific user.
	 * 
	 * @param user the user object representing the customer.
	 * @return a List of tickets booked by the user.
	 */
	public List<Ticket> getTicketsByUser(User user);
}
