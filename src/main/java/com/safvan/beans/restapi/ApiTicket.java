package com.safvan.beans.restapi;

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

import com.safvan.beans.Train;
import com.safvan.constants.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 *
 */
@Entity
@Table(name = "api_ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiTicket {
	/**
	 * The unique identifier for the ticket.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "apiUserTicketIdGenerator")
	@GenericGenerator(name = "apiUserTicketIdGenerator", strategy = "com.safvan.util.ApiUserTicketIdGenerator")
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

}
