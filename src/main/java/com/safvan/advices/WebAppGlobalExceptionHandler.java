package com.safvan.advices;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.safvan.beans.User;
import com.safvan.constants.UserRole;
import com.safvan.exception.train.TrainNotFoundException;
import com.safvan.service.ILoginManagementService;
import com.safvan.util.ExceptionLoggerUtil;
import com.safvan.util.UserUtils;

@ControllerAdvice
public class WebAppGlobalExceptionHandler {

	@Autowired
	private ILoginManagementService loginManagementService;

	@ExceptionHandler(TrainNotFoundException.class)
	public String handleTrainNotFoundException(TrainNotFoundException e, HttpServletRequest request, Model model) {

		// Logging exception
		ExceptionLoggerUtil.logException(e, request.getRequestURI());

		String sessionId = (String) request.getSession().getAttribute("sessionId");
		// finding userRole
		UserRole userRole = UserUtils.getUserRoleBySessionId(sessionId);

		String message = "Something went wrong!";
		String viewPage = null;

		System.out.println("***********************************************");

		if (userRole == UserRole.ADMIN) {
			viewPage = "admin/display_message";
		} else if (userRole == UserRole.CUSTOMER) {
			viewPage = "user/display_message";
		}

		message = e.getUserFriendlyMessage();
		model.addAttribute("message", message);
		return viewPage;
	}

	@ExceptionHandler(Exception.class)
	public String handleAllExceptions(Exception e) {
		System.out.println("Exception occurred: " + e.getMessage());
		return "user/display_message";
	}
}