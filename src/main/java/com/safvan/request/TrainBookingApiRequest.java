package com.safvan.request;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.safvan.beans.restapi.PaymentDetails;

import lombok.Data;

/**
 * Represents a request to book train tickets using rest api.
 */
@Component
@Data
public class TrainBookingApiRequest {

	/**
	 * The username of the user making the booking.
	 */
	private String userName;

	/**
	 * The date of the journey for the booking.
	 */
	private LocalDate journeyDate;

	/**
	 * The number of seats required for the booking.
	 */
	private Integer seatsRequired;

	/**
	 * The type of seat requested for the booking.
	 */
	private String seatType;

	/**
	 * The details of the selected train for the booking. Contains information about
	 * the train number, from and to stations, fare, and train name.
	 */
	private Long trainNo;

	/**
	 * The payment details associated with the booking.
	 */
	private PaymentDetails paymentDetails;

}
