package com.safvan.service;

import java.util.List;

import com.safvan.beans.TrainBean;
import com.safvan.execption.TrainException;
import com.safvan.execption.TrainNotFoundException;

public interface ITrainService {
	
	// to get all trains from DB
	public List<TrainBean> getAllTrains();
	
	public TrainBean getTrainByNumber(Long trainNo) throws TrainNotFoundException;
	
	public String addTrain(TrainBean trainBean) throws TrainException;
	
	public String deleteTrain(Long trainNo) throws TrainNotFoundException;
}
