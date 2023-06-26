package com.safvan.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safvan.beans.Train;

public interface ITrainDAO extends PagingAndSortingRepository<Train, Long>  {

}
