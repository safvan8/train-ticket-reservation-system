package com.safvan.advices.mvc;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.safvan.constants.UserRole;
import com.safvan.exception.mvc.booking.BookingException;
import com.safvan.exception.mvc.booking.BookingFailedException;
import com.safvan.exception.mvc.booking.NoEnoughSeatsForBooking;
import com.safvan.exception.mvc.login.LoginFailedException;
import com.safvan.exception.mvc.login.UserNotFoundException;
import com.safvan.exception.mvc.train.TrainException;
import com.safvan.exception.mvc.train.TrainNotFoundException;
import com.safvan.util.ExceptionLoggerUtil;
import com.safvan.util.UserUtils;

/**
 * Global Exception Handler for Standalone Spring Boot Application.
 * 
 * This class serves as the centralized exception handling point for the entire
 * application. It is annotated with @ControllerAdvice, allowing it to provide
 * common exception handling for all controllers.
 * 
 * Note: Controller advice classes provide a way to apply cross-cutting concerns
 * like exception handling, model attribute pre-processing, etc., to multiple
 * controllers in a centralized manner.
 * 
 * @Author: Safvan
 * @Version: 1.0
 * @Since: 1.0
 */
@ControllerAdvice
public class StandAloneGlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandAloneGlobalExceptionHandler.class);

	@Autowired
	private UserUtils userUtils;

	/**
	 * Exception handler method for handling ApiTrainNotFoundException.
	 * 
	 * This method is responsible for handling the custom exception
	 * ApiTrainNotFoundException, which may be thrown when a train is not found in
	 * the system. It logs the exception, determines the user's role based on the
	 * session, and displays an appropriate error page to the user based on their
	 * role (ADMIN_BASE_URI or CUSTOMER).
	 * 
	 * @param e       The ApiTrainNotFoundException that was thrown.
	 * @param request The HttpServletRequest for the current request.
	 * @param model   The Model object to add attributes for the view.
	 * 
	 *                Returns:
	 * @return The viewPage to display the error message.
	 */
	@ExceptionHandler(TrainNotFoundException.class)
	public String handleTrainNotFoundException(TrainNotFoundException e, HttpServletRequest request, Model model) {

		System.out.println("StandAloneGlobalExceptionHandler.handleTrainNotFoundException(************************)");

		// Logging the exception for debugging and tracing purposes
		ExceptionLoggerUtil.logException(e, request.getRequestURI());

		String sessionId = (String) request.getSession().getAttribute("sessionId");

		// Finding userRole based on sessionId.
		UserRole userRole = userUtils.getUserRoleBySessionId(sessionId);

		// default message setting.
		String message = "Something went wrong at :" + getClass().getName() + ".handleTrainNotFoundException(-,-,-)";
		String viewPage = null;

		// Displaying the user-based error page based on the user's role
		if (userRole != null) {
			if (userRole == UserRole.ADMIN) {
				viewPage = "admin/display_message";
			} else if (userRole == UserRole.CUSTOMER) {
				viewPage = "user/display_message";
			}

			// Setting the appropriate user-friendly message as an attribute in the model
			message = e.getUserFriendlyMessage();
		}
		model.addAttribute("message", message);
		return viewPage;
	}

	/**
	 * This exception handler is specifically for ApiTrainException, which will be
	 * raised only for admin features.
	 * 
	 * @param e       The ApiTrainException object
	 * @param request The HttpServletRequest
	 * @param model   The Model object to pass data to the view
	 * @return The view name to display the error message for admin users.
	 */

	@ExceptionHandler(TrainException.class)
	public String handleTrainException(TrainException e, HttpServletRequest request, Model model) {
		// Logging the exception
		ExceptionLoggerUtil.logException(e, request.getRequestURI());
		String message = e.getUserFriendlyMessage();
		model.addAttribute("message", message);

		return "admin/display_message";
	}

	/**
	 * Exception handler for ApiBookingException and its child classes.
	 * 
	 * @param e       The ApiBookingException object or its child classes.
	 * @param request The HttpServletRequest.
	 * @param model   The Model object to pass data to the view.
	 * @return The view name to display the error message for user-related booking
	 *         exceptions.
	 */
	@ExceptionHandler(value = { BookingException.class, NoEnoughSeatsForBooking.class, BookingFailedException.class })
	public String handleNoEnoughSeatsForBooking(BookingException e, HttpServletRequest request, Model model) {

		e.printStackTrace();

		LOGGER.error("Exception Occurred for the URL: {}", request.getRequestURI(), e);

		String message = e.getUserFriendlyMessage();
		model.addAttribute("message", message);
		return "user/display_message";
	}

	/**
	 * Exception handler for ApiLoginFailedException and ApiUserNotFoundException.
	 * 
	 * @param e       The ApiLoginFailedException or ApiUserNotFoundException object
	 *                that is raised when there is an issue related to user login.
	 * @param request The HttpServletRequest object containing the incoming request
	 *                information.
	 * @param model   The Model object to pass data to the view for rendering.
	 * @return The view name to display the error message based on the user's role
	 *         or the type of login-related exception raised.
	 */
	@ExceptionHandler(value = { LoginFailedException.class, UserNotFoundException.class })
	public String handleLonginRelatedExceptions(LoginFailedException e, HttpServletRequest request, Model model) {
		// Logging exception
		ExceptionLoggerUtil.logException(e, request.getRequestURI());

		String sessionId = (String) request.getSession().getAttribute("sessionId");
		// finding userRole
		UserRole userRole = userUtils.getUserRoleBySessionId(sessionId);

		String message = "Something went wrong at :" + getClass().getName() + ".handleTrainNotFoundException(-,-,-)";
		String viewPage = null;

		System.out.println("***********************************************");
		if (userRole != null) {
			if (userRole == UserRole.ADMIN) {
				viewPage = "admin/display_message";
			} else if (userRole == UserRole.CUSTOMER) {
				viewPage = "user/display_message";
			}

			message = e.getUserFriendlyMessage();
		}
		model.addAttribute("message", message);
		return viewPage;
	}

//	/**
//	 * Generic exception handler to handle any other unhandled exceptions.
//	 *
//	 * @param e The Exception object representing the unhandled exception.
//	 * @return The view name to display a generic error message for the user.
//	 */
//	@ExceptionHandler(Exception.class)
//	public String handleAllExceptions(Exception e) {
//		System.out.println("Exception occurred: " + e.getMessage());
//		return "user/display_message";
//	}
}