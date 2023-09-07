package com.safvan.service.mvc.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Test
	@DisplayName("Given - All Trains Available, When - Get All Trains is Called, Then -  A List of trains is returned")
	public void testGetAllTrainsWhenAllTrainsAvailable() {
		// Arrange

		Train train1 = new Train(1l, "TrainName1", "Bengaluru", "Kerala", 12, 50.0);
		Train train2 = new Train(1l, "TrainName2", "Bengaluru", "Kerala", 122, 502.0);
		Train train3 = new Train(1l, "TrainName3", "Bengaluru", "Kerala", 1242, 520.0);

		List<Train> expectedTrains = List.of(train1, train2, train3);

		given(trainRepository.findAll()).willReturn(expectedTrains);

		// Act
		List<Train> actualTrains = trainService.getAllTrains();

		// Assert
		assertEquals(expectedTrains.size(), actualTrains.size());
	}

	@Test
	@DisplayName("Given - train Number, When - getTrainByNumber is called, Then - train with the specific Id is returned")
	public void testGetTrainByNumber() {
		// Arrange
		Long trainNo = 10L;

		Train expectedTrain = new Train(10L, "TrainName", "Bengaluru", "Kerala", 1242, 520.0);

		given(trainRepository.findById(trainNo)).willReturn(Optional.of(expectedTrain)); // Return an Optional.

		// Act
		Train actualTrain = trainService.getTrainByNumber(trainNo);

		// Assert
		assertThat(actualTrain).isEqualTo(expectedTrain); // Use assertThat for object comparison.
	}

}
