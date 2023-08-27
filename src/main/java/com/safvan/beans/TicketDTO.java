package com.safvan.beans;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.safvan.constants.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Data Transfer Object (DTO) for transferring ticket data.
 * 
 * The TicketDTO class is used to transfer ticket-related information between
 * different layers or components of the application.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

	/**
	 * The unique identifier for the ticket.
	 */
	@Nullable
	private String ticketId;

	/**
	 * The transaction ID associated with the ticket.
	 */
	@Nullable
	private String transactionId;
	
	/**
	 * The status of the ticket.
	 */
	@Enumerated(EnumType.STRING)
	@Nullable
	private TicketStatus ticketStatus;

	/**
	 * The date of the journey.
	 * 
	 * This field represents the date of the journey for the ticket. It is annotated
	 * with @DateTimeFormat and configured with the pattern "yyyy-MM-dd". This
	 * allows automatic parsing and formatting of the date using the specified
	 * pattern. register the LocalDateFormatter bean in application context to
	 * enable the parsing and formatting of LocalDate objects.
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;

	/**
	 * The number of seats required for the ticket.
	 */
	private Integer seatsRequired;

	/**
	 * The type of seat for the ticket.
	 */
	private String seatType;

	/**
	 * The amount of the ticket.
	 */
	@Nullable
	private Double ticketAmount;

	/**
	 * The TrainDTO object associated with the ticket.
	 * 
	 * This DTO represents the train information associated with the ticket.
	 */
	@Nullable
	private TrainDTO trainDTO;

}
