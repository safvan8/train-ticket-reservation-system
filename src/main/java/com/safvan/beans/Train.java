package com.safvan.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Train entity in the application.
 * 
 * This class models a train with its details such as train number, name,
 * stations, available seats, and fare. Train objects are persisted in the
 * "train" table in the database.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "train")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "train_no")
	/**
	 * Unique identifier for train.
	 */
	private Long trainNo;

	/**
	 * The name of the train.
	 */
	@Column(name = "train_name")
	private String trainName;

	/**
	 * The starting station of the train.
	 */
	@Column(name = "from_station")
	private String fromStation;

	/**
	 * The destination station of the train.
	 */
	@Column(name = "to_station")
	private String toStation;

	/**
	 * The number of available seats on the train.
	 */
	@Column(name = "seats_available")
	private Integer seats;

	/**
	 * The fare for the train journey.
	 */
	private Double fare;
}
