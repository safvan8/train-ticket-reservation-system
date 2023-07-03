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

@Entity
@Table(name = "train")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "train_no")
	private Long trainNo;

	@Column(name = "train_name")
	private String trainName;
	
	@Column(name = "from_station")
	private String fromStation;
	
	@Column(name = "to_station")
	private String toStation;
	
	@Column(name="seats_available")
	private Integer seats;
	
	private Double fare;
}
