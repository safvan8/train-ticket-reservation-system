package com.safvan.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDTO {

	@Nullable
	private Long trainNo;

	@Nullable
	private String trainName;

	@Nullable
	private String fromStation;

	@Nullable
	private String toStation;

	@Nullable
	private Integer seats;

	@Nullable
	private Double fare;
}
