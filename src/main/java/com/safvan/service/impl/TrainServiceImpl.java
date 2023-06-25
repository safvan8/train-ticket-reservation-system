package com.safvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.TrainBean;
import com.safvan.dao.ITrainDAO;
import com.safvan.service.ITrainService;

@Service
public class TrainServiceImpl implements ITrainService {

	@Autowired
	private ITrainDAO trainDAO;
	
	@Override
	public List<TrainBean> getAllTrains() {
		System.out.println("TrainServiceImpl.getAllTrains()");
		return (List<TrainBean>) trainDAO.findAll();
	}

}
