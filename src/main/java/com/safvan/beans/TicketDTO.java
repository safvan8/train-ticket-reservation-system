package com.safvan.beans;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// only for trasnferring tciket data i

@Data
@AllArgsConstructor
public class TicketDTO {
	
	@Nullable
	private Long ticketId;

	@Nullable
	private String transactionId;
	
	// we implemnetd custom date formatter
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;

	private Integer seatsRequired;

	private String seatType;
	
	@Nullable
	private Double ticketAmount;
	
	@Nullable
	private TrainDTO trainDTO;

}