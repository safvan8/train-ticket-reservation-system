package com.safvan.beans;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.safvan.constants.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a ticket object for booking train tickets.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

	
	/**
	 * The unique identifier for the ticket.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "localUserTicketIdGenerator")
	@GenericGenerator(name = "localUserTicketIdGenerator", strategy = "com.safvan.util.LocalUserTicketIdGenerator")
	@Column(name = "ticket_id")
	private String ticketId;

	/**
	 * The transaction ID associated with the ticket.
	 */
	@Column(name = "transaction_id")
	private String transactionId;

	/**
	 * The status of the ticket.
	 */
	@Column(name = "ticket_status")
	@Enumerated(EnumType.STRING)
	private TicketStatus ticketStatus;

	/**
	 * The date of the journey.
	 */
	@Column(name = "journey_date")
	private LocalDate journeyDate;

	/**
	 * The number of seats required for the ticket.
	 */
	@Column(name = "seats_required")
	private Integer seatsRequired;

	/**
	 * The type of seat for the ticket.
	 */
	@Column(name = "seat_type")
	private String seatType;

	/**
	 * The amount of the ticket.
	 */
	@Column(name = "amount")
	private Double ticketAmount;

	/**
	 * The train associated with the ticket.
	 * 
	 * This is a many-to-one relationship where a ticket can be associated with a
	 * single train.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "trainNo")
	private Train train;

	/**
	 * The user associated with the ticket.
	 * 
	 * This is a many-to-one relationship where many tickets can be associated with
	 * a single user.
	 * 
	 * By using CascadeType.DETACH, when you save a Ticket entity, it will
	 * automatically link the associated User entity without making any changes to
	 * the User entity or persisting it. This cascade type is appropriate when you
	 * want to establish an association without modifying or persisting the
	 * associated entity.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "userId")
	private User user;
}
