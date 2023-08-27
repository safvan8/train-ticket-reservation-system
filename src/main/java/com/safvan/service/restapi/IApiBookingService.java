package com.safvan.service.restapi;

import com.safvan.beans.restapi.ApiTicket;
import com.safvan.exception.restapi.booking.ApiBookingFailedException;
import com.safvan.exception.restapi.booking.ApiNoEnoughSeatsForBooking;
import com.safvan.request.TrainBookingApiRequest;

/**
 * The IApiBookingService interface defines the contract for booking train
 * tickets through the REST API.
 * 
 * It provides a method for booking API tickets, taking a TrainBookingApiRequest
 * as input and returning an ApiTicket object.
 * 
 * This interface is responsible for handling exceptions related to booking
 * failures and insufficient seats.
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 */
public interface IApiBookingService {
	/**
	 * Books an API ticket based on the provided TrainBookingApiRequest.
	 * 
	 * @param trainBookingApiRequest The request containing booking details.
	 * @return An ApiTicket representing the booked ticket.
	 * @throws ApiNoEnoughSeatsForBooking If there are not enough seats available
	 *                                    for booking.
	 * @throws ApiBookingFailedException  If the booking process fails for any
	 *                                    reason.
	 */
	public ApiTicket bookApiTicket(TrainBookingApiRequest trainBookingApiRequest)
			throws ApiNoEnoughSeatsForBooking, ApiBookingFailedException;
}
