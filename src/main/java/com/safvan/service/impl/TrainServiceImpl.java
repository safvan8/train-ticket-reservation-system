package com.safvan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.safvan.beans.Train;
import com.safvan.dao.ITrainDAO;
import com.safvan.execption.TrainException;
import com.safvan.execption.TrainNotFoundException;
import com.safvan.service.ITrainService;

@Service
public class TrainServiceImpl implements ITrainService {

	@Autowired
	private ITrainDAO trainDAO;

	// to Display all trains
	@Override
	public List<Train> getAllTrains() {
		System.out.println("TrainServiceImpl.getAllTrains()");
		return (List<Train>) trainDAO.findAll();
	}

	// get a train using Id throw TNF Execption
	@Override
	public Train getTrainByNumber(Long trainNo) {

		Optional<Train> train = trainDAO.findById(trainNo);
		System.out.println("TrainServiceImpl.getTrainByNumber()");
		if (train.isPresent())
			return train.get();
		throw new TrainNotFoundException("Train Not Found with the Number : " + trainNo);
	}

	// to add a new Train
	@Override
	public String addTrain(Train train) {
		try {
			Train savedTrain = trainDAO.save(train);
			if (train.getTrainNo() == null)
			 return "Train added Sucessfull with train Number :" + savedTrain.getTrainNo();
			else
				return "Train details updated successfully for the train Number :"+train.getTrainNo();
				
		} catch (Exception e) {
			throw new TrainException("Error occured while adding a new Train :" + e.getMessage());
		}
	}

	// to delete a train
	@Override
	public String deleteTrain(Long trainNo) throws TrainNotFoundException {
		try {
			trainDAO.deleteById(trainNo);
			return "train with Number:" + trainNo + " deleted successfully";
		} catch (EmptyResultDataAccessException erd) {
			throw new TrainNotFoundException("Train Not Found With the Id :" + trainNo);
		} catch (Exception e) {
			throw new TrainException("Exception ouccured while deleteing train:" + e.getMessage());
		}
	}
}
