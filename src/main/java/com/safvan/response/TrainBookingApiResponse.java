package com.safvan.response;

import java.time.LocalDate;

import com.safvan.constants.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainBookingApiResponse {

	/**
	 * The unique identifier for the ticket.
	 */
	private String ticketId;

	/**
	 * The transaction ID associated with the ticket.
	 */
	private String transactionId;

	/**
	 * the status of the Ticktet.
	 */
	private TicketStatus ticketStatus;
	
	/**
	 * The date of the journey.
	 */
	private LocalDate journeyDate;

	/**
	 * The number of booked.
	 */
	private Integer seatsBooked;

	/**
	 * The type of seat for the ticket.
	 */
	private String seatType;

	/**
	 * The amount of the ticket.
	 */
	private Double ticketAmount;

	/**
	 * Unique identifier for train.
	 */
	private Long trainNo;

	/**
	 * The name of the train.
	 */
	private String trainName;

	/**
	 * The starting station of the train.
	 */
	private String fromStation;

	/**
	 * The destination station of the train.
	 */
	private String toStation;

}
