package com.safvan.service;

import java.util.List;

import com.safvan.beans.TrainBean;

public interface ITrainService {
	
	// to get all trains from DB
	public List<TrainBean> getAllTrains();
}
