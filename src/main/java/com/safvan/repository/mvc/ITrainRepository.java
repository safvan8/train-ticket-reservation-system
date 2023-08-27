package com.safvan.repository.mvc;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.safvan.beans.Train;

/**
 * ITrainRepository interface.
 * 
 * This interface extends the PagingAndSortingRepository interface provided by
 * Spring Data. It allows performing CRUD,Soring,paging operations and custom
 * queries on the Train entity.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
public interface ITrainRepository extends PagingAndSortingRepository<Train, Long> {
	/**
	 * Custom query to find trains between two stations. The query searches for
	 * trains where the fromStation and toStation match the provided parameters,
	 * considering case-insensitive comparison.
	 *
	 * @param fromStation the starting station
	 * @param toStation   the destination station
	 * @return a list of trains that match the given criteria
	 */
	@Query("FROM com.safvan.beans.Train WHERE ( UPPER(fromStation)=UPPER(:from) AND UPPER(toStation)=UPPER(:to)) OR (UPPER(fromStation)=UPPER(:to) AND UPPER(toStation)=UPPER(:from))")
	public List<Train> findTrainsBetweenStations(@Param("from") String fromStation, @Param("to") String toStation);
}
