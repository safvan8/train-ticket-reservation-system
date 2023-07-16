package com.safvan.advices;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.safvan.constants.UserRole;
import com.safvan.exception.booking.BookingException;
import com.safvan.exception.booking.BookingFailedException;
import com.safvan.exception.booking.NoEnoughSeatsForBooking;
import com.safvan.exception.login.LoginFailedException;
import com.safvan.exception.login.UserNotFoundException;
import com.safvan.exception.train.TrainException;
import com.safvan.exception.train.TrainNotFoundException;
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
 * Author: Safvan Version: 1.0 Since: 1.0
 */
@ControllerAdvice
public class StandAloneGlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandAloneGlobalExceptionHandler.class);

	@Autowired
	private UserUtils userUtils;

	/**
	 * Exception handler method for handling TrainNotFoundException.
	 * 
	 * This method is responsible for handling the custom exception
	 * TrainNotFoundException, which may be thrown when a train is not found in the
	 * system. It logs the exception, determines the user's role based on the
	 * session, and displays an appropriate error page to the user based on their
	 * role (ADMIN or CUSTOMER).
	 * 
	 * @param e       The TrainNotFoundException that was thrown.
	 * @param request The HttpServletRequest for the current request.
	 * @param model   The Model object to add attributes for the view.
	 * 
	 *                Returns:
	 * @return The viewPage to display the error message.
	 */
	@ExceptionHandler(TrainNotFoundException.class)
	public String handleTrainNotFoundException(TrainNotFoundException e, HttpServletRequest request, Model model) {

		// Logging exception
		ExceptionLoggerUtil.logException(e, request.getRequestURI());

		String sessionId = (String) request.getSession().getAttribute("sessionId");
		// finding userRole
		UserRole userRole = userUtils.getUserRoleBySessionId(sessionId);
		String message = "Something went wrong at :" + getClass().getName() + ".handleTrainNotFoundException(-,-,-)";
		System.out.println(message);
		String viewPage = null;

		System.out.println("***********************************************");
		if (userRole != null) {
			if (userRole == UserRole.ADMIN) {
				viewPage

						= "admin/display_message";
			} else if (userRole == UserRole.CUSTOMER) {
				viewPage = "user/display_message";
			}

			message = e.getUserFriendlyMessage();
		}
		model.addAttribute("message", message);
		return viewPage;
	}

	/**
	 * This exception handler only reuired for admin users, beause TrainException
	 * will be raised only for admin features.
	 * 
	 * @param e       the TrainException object
	 * @param request the HttpServletRequest
	 * @param model   the Model object to pass data to the view
	 */
	@ExceptionHandler(TrainException.class)
	public String handleTrainException(TrainException e, HttpServletRequest request, Model model) {
		// Logging exception
		ExceptionLoggerUtil.logException(e, request.getRequestURI());
		String message = e.getUserFriendlyMessage();
		model.addAttribute("message", message);

		return "admin/display_message";
	}

	/**
	 * 
	 * @param e       the BookingException or it's child classes.
	 * @param request the HttpServletREquest.
	 * @param model   the Model object to pass data to the view.
	 * @return the view name to diplay the error message.
	 */
	@ExceptionHandler(value = { BookingException.class, NoEnoughSeatsForBooking.class, BookingFailedException.class })
	public String handleNoEnoughSeatsForBooking(BookingException e, HttpServletRequest request, Model model) {

		LOGGER.error("Exception Occurred for the URL: {}", request.getRequestURI(), e);

		String message = e.getUserFriendlyMessage();
		model.addAttribute("message", message);
		return "user/display_message";
	}

	@ExceptionHandler(value = { LoginFailedException.class, UserNotFoundException.class })
	public String handleLonginRelatedExceptions(LoginFailedException e, HttpServletRequest request, Model model) {
		// Logging exception
		ExceptionLoggerUtil.logException(e, request.getRequestURI());

		String sessionId = (String) request.getSession().getAttribute("sessionId");
		// finding userRole
		UserRole userRole = userUtils.getUserRoleBySessionId(sessionId);
		String message = "Something went wrong at :" + getClass().getName() + ".handleTrainNotFoundException(-,-,-)";
		System.out.println(message);
		String viewPage = null;

		System.out.println("***********************************************");
		if (userRole != null) {
			if (userRole == UserRole.ADMIN) {
				viewPage

						= "admin/display_message";
			} else if (userRole == UserRole.CUSTOMER) {
				viewPage = "user/display_message";
			}

			message = e.getUserFriendlyMessage();
		}
		model.addAttribute("message", message);
		return viewPage;
	}

	@ExceptionHandler(Exception.class)
	public String handleAllExceptions(Exception e) {
		System.out.println("Exception occurred: " + e.getMessage());
		System.out.println();
		return "user/display_message";
	}
}