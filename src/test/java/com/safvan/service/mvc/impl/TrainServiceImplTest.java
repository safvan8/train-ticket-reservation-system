package com.safvan.service.mvc.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.safvan.beans.Train;
import com.safvan.exception.mvc.train.TrainException;
import com.safvan.exception.mvc.train.TrainNotFoundException;
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
	@DisplayName("Given - a valid train number, When - getTrainByNumber is called, Then - the train is returned")
	public void testGetTrainByNumberWhenTrainFound() {
		// Arrange
		Long trainNo = 10L;

		Train expectedTrain = new Train(10L, "TrainName", "Bengaluru", "Kerala", 1242, 520.0);

		given(trainRepository.findById(trainNo)).willReturn(Optional.of(expectedTrain)); // Return an Optional.

		// Act
		Train actualTrain = trainService.getTrainByNumber(trainNo);

		// Assert
		assertThat(actualTrain).isEqualTo(expectedTrain); // Use assertThat for object comparison.
	}

	@Test
	@DisplayName("Given - an invalid train number, When - getTrainByNumber is called, Then - TrainNotFoundException is thrown")
	public void testGetTrainByNumberWhenTrainNotFound() {
		// Arrange
		Long trainNo = 10L;
		given(trainRepository.findById(trainNo)).willReturn(Optional.empty());

		// Act and Assert
		assertThatThrownBy(() -> trainService.getTrainByNumber(trainNo)).isInstanceOf(TrainNotFoundException.class);
	}

	/*-----------------------------------------------------------------------------------------------------------------------------------*/

	@Test
	@DisplayName("Given - a new train, When - saveOrUpdateTrain is called, Then - success message for adding a train is returned")
	public void testSaveOrUpdateTrainForNewTrain() {
		// Arrange
		Train newTrain = new Train(null, "TrainName", "Bengaluru", "Kerala", 1242, 520.0);
		Train savedTrain = new Train(10L, "TrainName", "Bengaluru", "Kerala", 1242, 520.0);

		given(trainRepository.save(newTrain)).willReturn(savedTrain);

		// Act
		String result = trainService.saveOrUpdateTrain(newTrain);

		// Assert
		assertThat(result).isEqualTo("Train added successfully with train Number: " + savedTrain.getTrainNo());
	}

	@Test
	@DisplayName("Given - an existing train, When - saveOrUpdateTrain is called, Then - success message for updating a train is returned")
	public void testSaveOrUpdateTrainForExistingTrain() {
		// Arrange
		Train existingTrain = new Train(10L, "TrainName", "Bengaluru", "Kerala", 1242, 520.0);
		Train updatedTrain = new Train(10L, "UpdatedTrainName", "NewOrigin", "NewDestination", 1500, 700.0);

		given(trainRepository.save(existingTrain)).willReturn(updatedTrain);

		// Act
		String result = trainService.saveOrUpdateTrain(existingTrain);

		// Assert
		assertThat(result)
				.isEqualTo("Train details updated successfully for the train Number: " + existingTrain.getTrainNo());
	}

	@Test
	@DisplayName("Given - a train and an exception during save, When - saveOrUpdateTrain is called, Then - TrainException is thrown")
	public void testSaveOrUpdateTrainWithException() {
		// Arrange
		Train train = new Train(10L, "TrainName", "Bengaluru", "Kerala", 1242, 520.0);
		given(trainRepository.save(train)).willThrow(new RuntimeException("Some error occurred"));

		// Act and Assert
		assertThatThrownBy(() -> trainService.saveOrUpdateTrain(train)).isInstanceOf(TrainException.class);
	}

	/*-----------------------------------------------------------------------------------------------------------------------------------*/

	@Test
	@DisplayName("Given - an existing train, When - deleteTrain is called, Then - success message for deletion is returned")
	public void testDeleteTrainForExistingTrain() {
		// Arrange
		Long trainNo = 10L;

		// Mock successful deletion
		BDDMockito.willDoNothing().given(trainRepository).deleteById(trainNo);

		// Act
		String result = trainService.deleteTrain(trainNo);

		// Assert
		assertThat(result).isEqualTo("Train with Number: " + trainNo + " deleted successfully");
	}

	@Test
	@DisplayName("Given - a non-existing train, When - deleteTrain is called, Then - TrainNotFoundException is thrown")
	public void testDeleteTrainForNonExistingTrain() {
		// Arrange
		Long trainNo = 10L;

		// Mock EmptyResultDataAccessException when deleting
		BDDMockito.willThrow(EmptyResultDataAccessException.class).given(trainRepository).deleteById(trainNo);

		// Act
		Throwable thrownException = catchThrowable(() -> trainService.deleteTrain(trainNo));

		// Assert
		assertThat(thrownException).isInstanceOf(TrainNotFoundException.class);
	}

	@Test
	@DisplayName("Given - an exception during deletion, When - deleteTrain is called, Then - TrainException is thrown")
	public void testDeleteTrainWithException() {
		// Arrange
		Long trainNo = 10L;

		// Mock an exception when deleting
		BDDMockito.willThrow(new RuntimeException("Some error occurred")).given(trainRepository).deleteById(trainNo);

		// Act
		Throwable thrownException = catchThrowable(() -> trainService.deleteTrain(trainNo));

		// Assert
		assertThat(thrownException).isInstanceOf(TrainException.class);

	}

	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Given source and destination stations, When getTrainsBetweenStations is called, Then a list of trains between the stations is returned")
	public void testGetTrainsBetweenStations() {
		// Arrange - Given
		String fromStation = "Bengaluru";
		String toStation = "Kerala";

		List<Train> expectedTrains = new ArrayList<>();
		Train train1 = new Train(1L, "TrainName1", fromStation, toStation, 12, 50.0);
		Train train2 = new Train(2L, "TrainName2", fromStation, toStation, 12, 50.0);
		expectedTrains.add(train1);
		expectedTrains.add(train2);

		given(trainRepository.findTrainsBetweenStations(fromStation, toStation)).willReturn(expectedTrains);

		// Act - When
		List<Train> actualTrains = trainService.getTrainsBetweenStations(fromStation, toStation);

		// Assert - Then
		assertThat(actualTrains).isNotNull();
		assertThat(actualTrains).hasSize(2);
		assertThat(actualTrains).containsExactlyInAnyOrder(train1, train2);
	}

}
