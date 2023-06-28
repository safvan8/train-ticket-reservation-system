package com.safvan.service;

import java.util.List;

import com.safvan.beans.Train;
import com.safvan.exception.TrainException;
import com.safvan.exception.TrainNotFoundException;

public interface ITrainService {
	
	// to get all trains from DB
	public List<Train> getAllTrains();
	
	public Train getTrainByNumber(Long trainNo) throws TrainNotFoundException;
	
	public String saveOrUpdateTrain(Train train) throws TrainException;
	
	public String deleteTrain(Long trainNo) throws TrainNotFoundException;
	
	public List<Train> getTrainsBetweenStations(String fromStation,String toStation);
}
