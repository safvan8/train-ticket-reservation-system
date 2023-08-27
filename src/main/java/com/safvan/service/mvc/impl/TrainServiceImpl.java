package com.safvan.service.mvc.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.safvan.beans.Train;
import com.safvan.exception.mvc.train.TrainException;
import com.safvan.exception.mvc.train.TrainNotFoundException;
import com.safvan.repository.mvc.ITrainRepository;
import com.safvan.service.mvc.ITrainService;

/**
 * ApiTrainServiceImpl class is an implementation of the IApiTrainService interface.
 * 
 * This class provides the implementation for various operations related to
 * trains, such as retrieving train details,
 * 
 * adding or updating train information, deleting trains, and finding trains
 * between stations.
 * 
 * This class interacts with TrainRepository to perform these operations.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */

@Service
public class TrainServiceImpl implements ITrainService {

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
		throw new TrainNotFoundException(Thread.currentThread().getStackTrace(), userFriendlyMessage);
	}

	/**
	 * Saves or updates a train in the database.
	 *
	 * @param train The train to be saved or updated.
	 * @return A success message indicating the action performed.
	 * @throws ApiTrainException If an error occurs while adding or updating the train.
	 */
	@Override
	public String saveOrUpdateTrain(Train train) {
		try {
			Train savedTrain = trainRepository.save(train);
			if (train.getTrainNo() == null)
				return "Train added successfully with train Number: " + savedTrain.getTrainNo();
			else
				return "Train details updated successfully for the train Number: " + train.getTrainNo();

		} catch (Exception e) {
			throw new TrainException(e.getStackTrace(),
					"Error occurred while saving or updating a train with  train Number: " + train.getTrainNo());
		}
	}

	/**
	 * Deletes a train from the database.
	 *
	 * @param trainNo The train number to be deleted.
	 * @return A success message indicating the deletion.
	 * @throws ApiTrainNotFoundException If the train is not found.
	 * @throws ApiTrainException         If an error occurs while deleting the train.
	 */
	@Override
	public String deleteTrain(Long trainNo) throws TrainNotFoundException {

		String userFriendlyMessage = null;

		try {
			trainRepository.deleteById(trainNo);
			return "Train with Number: " + trainNo + " deleted successfully";
		} catch (EmptyResultDataAccessException erd) {
			userFriendlyMessage = "Train Not Found with the Number : " + trainNo;
			throw new TrainNotFoundException(erd.getStackTrace(), userFriendlyMessage);
		} catch (Exception e) {
			userFriendlyMessage = "Exception occurred while deleting train with Number: " + trainNo;
			throw new TrainException(e.getStackTrace(), userFriendlyMessage);
		}
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
