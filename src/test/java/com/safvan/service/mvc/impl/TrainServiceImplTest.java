package com.safvan.service.mvc.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safvan.beans.Train;
import com.safvan.repository.mvc.ITrainRepository;

@ExtendWith(MockitoExtension.class)
public class TrainServiceImplTest {

	@InjectMocks
	private TrainServiceImpl trainService;

	@Mock
	private ITrainRepository trainRepository;

	@Test
	@DisplayName("Given - No Trains Available, When - Get All Trains is Called, Then - An Empty List is Returned")
	public void testGetAllTrainsWhenNoTrainsAvailable() {
		// Arrange
		List<Train> expectedTrains = new ArrayList<>();

		given(trainRepository.findAll()).willReturn(expectedTrains);

		// Act
		List<Train> actualTrains = trainService.getAllTrains();

		// Assert
		assertEquals(expectedTrains.size(), actualTrains.size());
	}

}
