package com.safvan.service;

import java.util.List;

import com.safvan.beans.Train;
import com.safvan.exception.train.TrainException;
import com.safvan.exception.train.TrainNotFoundException;

/**
 * The ITrainService interface defines the contract for performing operations related to trains.
 * 
 * It provides methods for retrieving train details, adding or updating train information,
 * deleting trains, and finding trains between stations.
 *
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
public interface ITrainService {
	
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
	 * @throws TrainNotFoundException If the train with the specified number is not found.
	 */
	public Train getTrainByNumber(Long trainNo) throws TrainNotFoundException;
	
	/**
	 * Saves or updates the details of a train.
	 * 
	 * @param train The Train object containing the train details to be saved or updated.
	 * @return A message indicating the status of the operation.
	 * @throws TrainException If an error occurs while saving or updating the train.
	 */
	public String saveOrUpdateTrain(Train train) throws TrainException;
	
	/**
	 * Deletes a train based on the train number.
	 * 
	 * @param trainNo The train number of the train to be deleted.
	 * @return A message indicating the status of the deletion.
	 * @throws TrainNotFoundException If the train with the specified number is not found.
	 */
	public String deleteTrain(Long trainNo) throws TrainNotFoundException;
	
	/**
	 * Finds trains between the specified source and destination stations.
	 * 
	 * @param fromStation The source station.
	 * @param toStation The destination station.
	 * @return A list of Train objects representing the trains between the specified stations.
	 */
	public List<Train> getTrainsBetweenStations(String fromStation, String toStation);
}
