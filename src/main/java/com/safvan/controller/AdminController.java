package com.safvan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safvan.beans.Train;
import com.safvan.constants.endpoints.AdminEndpoints;
import com.safvan.exception.restapi.train.ApiTrainException;
import com.safvan.exception.restapi.train.ApiTrainNotFoundException;
import com.safvan.service.mvc.ITrainService;

/**
 * AdminController class handles the HTTP requests and manages the admin-related
 * operations. This class is responsible for handling various actions related to
 * managing trains in the system. It interacts with the TrainService to perform
 * CRUD operations on trains.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(AdminEndpoints.ADMIN_BASE_URI)
public class AdminController {

	@Autowired
	private ITrainService trainService;

	/**
	 * Displays the home page for the admin.
	 *
	 * @return The view name for the admin home page.
	 */
	@GetMapping(value = { "/", AdminEndpoints.SHOW_HOME })
	public String showHomePage() {
		return "admin/admin_home";
	}

	/**
	 * Retrieves all available trains and forwards to the view to display them.
	 *
	 * @param model The model object to pass data to the view.
	 * @return The view name for displaying all trains.
	 */
	@GetMapping(AdminEndpoints.VIEW_ALL_TRAINS)
	public String viewAllTrainsForward(Map<String, Object> model) {

		List<Train> allTrains = trainService.getAllTrains();

		model.put("allTrains", allTrains);

		return "admin/view_trains";
	}

	/**
	 * Forwards to the search train form page.
	 *
	 * @return The view name for the search train form.
	 */
	@GetMapping(AdminEndpoints.SHOW_SEARCH_TRAIN_BY_NUMBER_FORM)
	public String searchTrainByNumberForward() {
		return "admin/search_train_form";
	}

	/**
	 * Searches for a train by its number and forwards to the view to display the
	 * result.
	 *
	 * @param trainNo The train number to search for.
	 * @param model   The model object to pass data to the view.
	 * @return The view name for displaying the search train result.
	 * @throws ApiTrainNotFoundException If the train is not found.
	 */
	@GetMapping(AdminEndpoints.SHOW_SEARCH_TRAIN_BY_NUMBER_RESULT)
	public String searchTrainByNumberResult(@RequestParam Long trainNo, Map<String, Object> model) {
		Train train = trainService.getTrainByNumber(trainNo);

		model.put("train", train);

		return "admin/search_train_result";
	}

	/**
	 * Forwards to the add train form page.
	 *
	 * @return The view name for the add train form.
	 */
	@GetMapping(AdminEndpoints.SHOW_ADD_TRAIN_FORM)
	public String addTrainForward() {
		return "admin/add_train_form";
	}

	/**
	 * Saves or updates a train.
	 * 
	 * This method is used to add a new train by performing an insert operation, or
	 * it can be used to edit the details of an existing train based on its ID. The
	 * train object is received as a model attribute and passed to the train service
	 * for saving or updating in the database.
	 * 
	 * @param train The train object to be saved or updated.
	 * @param model The model object to pass data to the view.
	 * @return The view name for displaying the save or update result message.
	 * @throws ApiTrainException If an error occurs while saving or updating the
	 *                           train.
	 */
	@PostMapping(AdminEndpoints.SAVE_OR_UPDATE_TRAIN)
	public String saveOrUpdateTrain(@ModelAttribute Train train, Map<String, Object> model) {

		String message = trainService.saveOrUpdateTrain(train);

		model.put("message", message);

		return "admin/display_message";
	}

	/**
	 * Forwards to the update train form page.
	 *
	 * @return The view name for the update train form.
	 */
	@GetMapping(AdminEndpoints.SHOW_UPDATE_TRAIN_INITIAL_FORM)
	public String updateTrainFwd() {
		return "admin/update_train_page";
	}

	/**
	 * Shows the edit form for a specific train based on trainNo.
	 * 
	 * This method receives the train number as a request parameter and retrieves
	 * the corresponding train details from the train service for updating it. The
	 * train object is then added to the model and passed to the view for displaying
	 * the edit form.
	 * 
	 * @param trainNo The train number.
	 * @param model   The empty model object to pass data to the view.
	 * @return The view name for the edit train details form.
	 * @throws ApiTrainNotFoundException If the train is not found.
	 */
	@PostMapping(AdminEndpoints.SHOW_TRAIN_EDIT_FORM_TO_UPDATE_BY_NUMBER)
	public String showEditForm(@RequestParam Long trainNo, Map<String, Object> model) {

		Train train = trainService.getTrainByNumber(trainNo);

		// passing the retrived train to view for editing it.
		model.put("train", train);
		return "admin/edit_train_details_form";
	}

	/**
	 * Forwards to the delete train form page.
	 *
	 * @return The view name for the delete train form.
	 */
	@GetMapping(AdminEndpoints.SHOW_DELETE_TRAIN_FORM)
	public String deleteTrainForward() {
		return "admin/delete_train_form";
	}

	/**
	 * Deletes an existing train.
	 * 
	 * This method receives the train number as a request parameter and deletes the
	 * corresponding train from the database using the train service. The result
	 * message is added to the model and passed to the view for displaying.
	 * 
	 * @param trainNo The train number for deleting.
	 * @param model   The model object to pass deletion result to the view.
	 * @return The view name for displaying the delete result message.
	 * @throws ApiTrainNotFoundException If the train is not found.
	 * @throws ApiTrainException         If an error occurs while deleting the
	 *                                   train.
	 */
	@PostMapping(AdminEndpoints.DELETE_TRAIN_BY_NUMBER)
	public String deleteTrain(@RequestParam Long trainNo, Map<String, Object> model) {

		String message = trainService.deleteTrain(trainNo);

		model.put("message", message);

		return "admin/display_message";
	}
}
