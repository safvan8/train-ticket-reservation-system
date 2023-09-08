package com.safvan.service.mvc.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.safvan.beans.Ticket;
import com.safvan.beans.Train;
import com.safvan.constants.TicketStatus;
import com.safvan.exception.mvc.booking.BookingFailedException;
import com.safvan.exception.mvc.booking.NoEnoughSeatsForBooking;
import com.safvan.repository.mvc.ITrainRepository;
import com.safvan.service.mvc.ITrainService;

@SpringBootTest
public class BookingServiceImplTest {

	@InjectMocks
	private BookingServiceImpl bookingService;

	@Mock
	private ITrainService trainService;

	@Mock
	private ITrainRepository trainRepository;

	@Test
	@DisplayName("Given enough available seats, When bookTicket is called, Then a ticket is successfully booked")
	public void testBookTicketWithEnoughSeats() {
		// Arrange - Given
		Train train = new Train(1L, "TrainName", "Bengaluru", "Kerala", 100, 50.0);
		Ticket ticketRequest = new Ticket();
		ticketRequest.setTrain(train);
		ticketRequest.setSeatsRequired(5);
		// Set other necessary ticket details

		given(trainService.getTrainByNumber(ticketRequest.getTrain().getTrainNo())).willReturn(train);

		// Act - When
		Ticket bookedTicket = bookingService.bookTicket(ticketRequest);

		// Assert - Then
		assertThat(bookedTicket).isNotNull();
		assertThat(bookedTicket.getTicketStatus()).isEqualTo(TicketStatus.BOOKED);

	}

	@Test
	@DisplayName("Given not enough available seats, When bookTicket is called, Then NoEnoughSeatsForBooking exception is thrown")
	public void testBookTicketWithNotEnoughSeats() {
		// Arrange - Given
		Train train = new Train(1L, "TrainName", "Bengaluru", "Kerala", 10, 50.0);
		Ticket ticketRequest = new Ticket();
		ticketRequest.setTrain(train);
		ticketRequest.setSeatsRequired(20); // Requesting more seats than available

		given(trainService.getTrainByNumber(ticketRequest.getTrain().getTrainNo())).willReturn(train);

		// Act and Assert
		assertThatThrownBy(() -> bookingService.bookTicket(ticketRequest)).isInstanceOf(NoEnoughSeatsForBooking.class)
				.hasMessageContaining("Only " + train.getSeats() + " seats are available on this train!");
	}

	@Test
	@DisplayName("Given an exception during booking, When bookTicket is called, Then BookingFailedException is thrown")
	public void testBookTicketWithException() {
		// Arrange - Given
		Train train = new Train(1L, "TrainName", "Bengaluru", "Kerala", 100, 50.0);
		Ticket ticketRequest = new Ticket();
		ticketRequest.setTrain(train);
		ticketRequest.setSeatsRequired(5);
		// Set other necessary ticket details

		given(trainService.getTrainByNumber(ticketRequest.getTrain().getTrainNo())).willReturn(train);
		// Mock the ticketRepository or any other dependency to throw an exception

		// Act and Assert
		assertThatThrownBy(() -> bookingService.bookTicket(ticketRequest)).isInstanceOf(BookingFailedException.class)
				.hasMessageContaining("Booking failed for the train number: " + train.getTrainNo());
	}
}
