package com.safvan.beans;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticket_id")
	private Long ticketId;

	/**
	 * one-to-many relationship where a Train can have multiple associated Ticket
	 * objects, and each Ticket is linked to a specific Train.
	 */

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "journey_date")
	private LocalDate journeyDate;

	@Column(name = "seats_required")
	private Integer seatsRequired;

	@Column(name = "seat_type")
	private String seatType;

	@Column(name = "amount")
	private Double ticketAmount;

	/**
	 * By setting fetch = FetchType.LAZY on the @ManyToOne relationship, the Train
	 * object will be lazily loaded, meaning it will be fetched from the database
	 * only when accessed explicitly. This way, when you save a Ticket, the
	 * associated Train will not be saved automatically. However, when you retrieve
	 * a Ticket, you can access its Train object and it will be fetched from the
	 * database. Similarly, when you update the Train, changes will reflect in the
	 * associated Ticket objects.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "trainNo")
	private Train train;

	/**
	 * With this configuration, when you update the Train entity and save it, the
	 * associated Ticket entities will be automatically updated in the database.
	 * 
	 * cascade = CascadeType.MERGE
	 */
}
