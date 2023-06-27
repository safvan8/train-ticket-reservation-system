package com.safvan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.safvan.beans.Train;

public interface ITrainDAO extends PagingAndSortingRepository<Train, Long> {

	@Query("FROM com.safvan.beans.Train WHERE UPPER(fromStation)=UPPER(:from) AND UPPER(toStation)=UPPER(:to)")
	public List<Train> findTrainsBetweenStations(@Param("from") String fromStation, @Param("to") String toStation);
}
