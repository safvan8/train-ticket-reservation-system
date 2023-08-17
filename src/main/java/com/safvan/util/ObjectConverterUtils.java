package com.safvan.util;

import com.safvan.beans.restapi.ApiTicket;
import com.safvan.response.TrainBookingApiResponse;

public class ObjectConverterUtils {

	public static TrainBookingApiResponse convertApiTicketToApiResponse(ApiTicket ticket) {

		TrainBookingApiResponse response = new TrainBookingApiResponse();

		response.setTicketId(ticket.getTicketId());
		response.setTransactionId(ticket.getTransactionId());
		response.setTicketStatus(ticket.getTicketStatus());
		response.setJourneyDate(ticket.getJourneyDate());
		response.setSeatType(ticket.getSeatType());
		response.setSeatsBooked(ticket.getSeatsRequired());
		response.setTicketAmount(ticket.getTicketAmount());
		response.setTrainNo(ticket.getTrain().getTrainNo());
		response.setTrainName(ticket.getTrain().getTrainName());
		response.setFromStation(ticket.getTrain().getFromStation());
		response.setToStation(ticket.getTrain().getToStation());

		return response;
	}
}
