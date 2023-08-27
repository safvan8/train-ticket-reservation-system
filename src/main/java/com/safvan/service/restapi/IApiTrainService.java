package com.safvan.service.restapi;

import java.util.List;

import com.safvan.beans.Train;
import com.safvan.exception.restapi.train.ApiTrainNotFoundException;

/**
 * The IApiTrainService interface defines the contract for performing operations
 * related to trains in the context of REST API services.
 * 
 * It provides methods for retrieving train details, finding trains between
 * stations, and accessing specific train information.
 *
 * @author Safvan
 * @version 2.0
 * @since 2.0
 */
public interface IApiTrainService {

	/**
	 * Retrieves a list of all trains from the database.
	 * 
	 * @return A list of Train objects representing all trains in the system.
	 */
	public List<Train> getAllTrains();

	/**
	 * Retrieves a specific train based on the train number.
	 * 
	 * @param trainNo The train number of the train to retrive object.
	 * @return The Train object corresponding to the provided train number.
	 * @throws ApiTrainNotFoundException If the train with the specified number is
	 *                                   not found.
	 */
	public Train getTrainByNumber(Long trainNo) throws ApiTrainNotFoundException;

	/**
	 * Finds trains between the specified source and destination stations.
	 * 
	 * @param fromStation The source station.
	 * @param toStation   The destination station.
	 * @return A list of Train objects representing the trains between the specified
	 *         stations.
	 */
	public List<Train> getTrainsBetweenStations(String fromStation, String toStation);
}
