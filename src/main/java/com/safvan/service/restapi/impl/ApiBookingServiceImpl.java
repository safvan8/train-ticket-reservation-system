package com.safvan.service.restapi.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.Train;
import com.safvan.beans.restapi.ApiTicket;
import com.safvan.constants.TicketStatus;
import com.safvan.exception.restapi.booking.ApiBookingFailedException;
import com.safvan.exception.restapi.booking.ApiNoEnoughSeatsForBooking;
import com.safvan.repository.restapi.IApiTicketRespository;
import com.safvan.request.TrainBookingApiRequest;
import com.safvan.service.mvc.ITrainService;
import com.safvan.service.restapi.IApiBookingService;

/**
 * The ApiBookingServiceImpl class is an implementation of the
 * IApiBookingService interface, responsible for handling booking operations
 * through the REST API.
 * 
 * This class interacts with the ITrainService and IApiTicketRepository to
 * manage train bookings and ticket creations.
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 */
@Service
public class ApiBookingServiceImpl implements IApiBookingService {

	@Autowired
	private ITrainService trainService;

	@Autowired
	private IApiTicketRespository apiTicketRespository;

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
	@Override
	@Transactional
	public ApiTicket bookApiTicket(TrainBookingApiRequest trainBookingApiRequest) {

		// fetching complete train details and saving to ticket before booking
		// train starting, setdination, fare all details presernt the object
		Train trainForBooking = trainService.getTrainByNumber(trainBookingApiRequest.getTrainNo());

		Integer seatsAvailable = trainForBooking.getSeats();
		Integer seatsRequired = trainBookingApiRequest.getSeatsRequired();

		ApiTicket apiTicketResult = null;

		if (seatsAvailable < seatsRequired) {
			String userFriendlyMessage = "Only " + seatsAvailable + " seats are available on this train!";
			throw new ApiNoEnoughSeatsForBooking(Thread.currentThread().getStackTrace(), userFriendlyMessage);
		} else {
			seatsAvailable = seatsAvailable - seatsRequired;

			// update seats avaialble
			trainForBooking.setSeats(seatsAvailable);

			try {

				String transactionId = UUID.randomUUID().toString();
				Double fare = trainForBooking.getFare();
				Double totalAmount = fare * seatsRequired;

				ApiTicket apiTicket = new ApiTicket();
				apiTicket.setTransactionId(transactionId);
				apiTicket.setTicketStatus(TicketStatus.BOOKED);
				apiTicket.setJourneyDate(trainBookingApiRequest.getJourneyDate());
				apiTicket.setSeatsRequired(seatsRequired);
				apiTicket.setSeatType(trainBookingApiRequest.getSeatType());
				apiTicket.setTicketAmount(totalAmount);
				// adding train's complete information to ticketBookingResult object for
				// displaying
				apiTicket.setTrain(trainForBooking);

				// writing the code in same place for concurrent access optimization

				// creating ticket and confirmation
				apiTicketResult = apiTicketRespository.save(apiTicket);

				// updating train if ticket booked succesfully
				trainService.saveOrUpdateTrain(trainForBooking);

				// to genarate tickets , updating the details

			} catch (Exception e) {
				String userFriendlyMessage = "Booking failed for the train number: " + trainForBooking.getTrainNo();
				throw new ApiBookingFailedException(Thread.currentThread().getStackTrace(), userFriendlyMessage);
			}
		}

		return apiTicketResult;
	}
}
