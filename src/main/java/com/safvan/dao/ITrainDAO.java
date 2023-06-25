package com.safvan.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safvan.beans.TrainBean;

public interface ITrainDAO extends PagingAndSortingRepository<TrainBean, Long>  {

}
