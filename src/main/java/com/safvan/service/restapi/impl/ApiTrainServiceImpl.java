package com.safvan.service.restapi.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.Train;
import com.safvan.exception.restapi.train.ApiTrainNotFoundException;
import com.safvan.repository.mvc.ITrainRepository;
import com.safvan.service.restapi.IApiTrainService;

/**
 * ApiTrainServiceImpl class is an implementation of the IApiTrainService
 * interface, providing functionalities related to train operations via REST API.
 * 
 * This class offers methods for retrieving train details, finding trains between
 * stations, and accessing specific train information.
 * 
 * The class interacts with the TrainRepository to perform these operations.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */

@Service
public class ApiTrainServiceImpl implements IApiTrainService {

	@Autowired
	private ITrainRepository trainRepository;

	/**
	 * Retrieves all trains from the database.
	 *
	 * @return List of all trains.
	 */
	@Override
	public List<Train> getAllTrains() {
		System.out.println("ApiTrainServiceImpl.getAllTrains()");
		return (List<Train>) trainRepository.findAll();
	}

	/**
	 * Retrieves a train by its train number.
	 *
	 * @param trainNo The train number.
	 * @return The train with the specified number.
	 * @throws ApiTrainNotFoundException If the train is not found.
	 */
	@Override
	public Train getTrainByNumber(Long trainNo) {
		Optional<Train> train = trainRepository.findById(trainNo);
		System.out.println("ApiTrainServiceImpl.getTrainByNumber()");
		if (train.isPresent())
			return train.get();
		// throwing execption if train not found

		String userFriendlyMessage = "Train Not Found with the Number : " + trainNo;
		throw new ApiTrainNotFoundException(Thread.currentThread().getStackTrace(), userFriendlyMessage);
	}

	/**
	 * Retrieves trains between two stations.
	 *
	 * @param fromStation The source station.
	 * @param toStation   The destination station.
	 * @return List of trains between the specified stations.
	 */
	@Override
	public List<Train> getTrainsBetweenStations(String fromStation, String toStation) {

		return trainRepository.findTrainsBetweenStations(fromStation, toStation);
	}
}
