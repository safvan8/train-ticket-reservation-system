package com.safvan.beans;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TrainDTO class represents a Data Transfer Object (DTO) for Train objects.
 * 
 * This class is used for transferring train-related data between different
 * layers or components of the application. It contains train information such
 * as train number, name, stations, available seats, and fare.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDTO {

	/**
	 * The train number.
	 */
	@Nullable
	private Long trainNo;

	/**
	 * The name of the train.
	 */
	@Nullable
	private String trainName;

	/**
	 * The starting station of the train.
	 */
	@Nullable
	private String fromStation;

	/**
	 * The destination station of the train.
	 */
	@Nullable
	private String toStation;

	/**
	 * The number of available seats on the train.
	 */
	@Nullable
	private Integer seats;

	/**
	 * The fare for the train journey.
	 */
	@Nullable
	private Double fare;
}
