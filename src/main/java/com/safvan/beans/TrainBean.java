package com.safvan.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="train_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trainNo;
	private String trainName;
	private String fromStation;
	private String toStation;
	private Integer seats;
	private Double fair;
}
