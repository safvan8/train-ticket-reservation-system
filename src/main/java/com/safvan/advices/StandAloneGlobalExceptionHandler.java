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

@ControllerAdvice
public class StandAloneGlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandAloneGlobalExceptionHandler.class);

	@Autowired
	private UserUtils userUtils;

	/**
	 * for handling trasin not found eception
	 * 
	 * @param e
	 * @param request
	 * @param model
	 * @return viewPage the page to diplay the error message.
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