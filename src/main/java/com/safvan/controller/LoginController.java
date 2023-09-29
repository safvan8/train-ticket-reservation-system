package com.safvan.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safvan.beans.User;
import com.safvan.constants.UserRole;
import com.safvan.constants.endpoints.LoginEndpoints;
import com.safvan.service.mvc.ILoginManagementService;

/**
 * Controller class responsible for handling all login related functionalities.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Controller
public class LoginController {

	@Autowired
	private ILoginManagementService loginManagementService;

	/**
	 * Displays the login page.
	 * 
	 * @return the view name for the login page.
	 */
	@GetMapping(value = { LoginEndpoints.LOGIN, "/" })
	public String showLoginPage() {
		return "login";
	}

	/**
	 * Processes the login request and authenticates the user.
	 * 
	 * @param username The username entered by the user.
	 * @param password The password entered by the user.
	 * @param session  The HttpSession object.
	 * @param model    The model object to pass data to the view.
	 * @return The view name based on the login result.
	 */
	@PostMapping(LoginEndpoints.LOGIN)
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
			Map<String, Object> model) {

		// passing user ented username ,and password for authentication.
		User user = loginManagementService.authenticateUser(username, password);

		System.out.println("LoginController.login()");

		if (user != null) {

			String sessionId = UUID.randomUUID().toString(); // Generate a unique session ID
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("user", user);

			// session expiry set for 10 minutes.
			session.setMaxInactiveInterval(10 * 60);
			// Store the session ID and associate it with the user in the database.
			loginManagementService.storeUserSession(user.getUserId(), sessionId);

			return "redirect:/home";
		} else {
			model.put("loginFailedMessage", "Login Failed, Invalid username or Password");
			return "/login"; // Return to the login page with an error message.
		}
	}

	/**
	 * Displays the home page based on the user's role after successfull login.
	 * Users role is found using sessionId.
	 *
	 * @param session The HttpSession object.
	 * @return The view name for the home page.
	 */
	@GetMapping(LoginEndpoints.SHOW_HOME)
	public String showHome(HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		System.out.println(sessionId);

		User user = null;
		// if sessionId is not null , find user by using that sessionId.
		if (sessionId != null) {
			user = loginManagementService.getUserbySessionId(sessionId);
		}

		// retrieve the user based on the session ID from the database.
		if (sessionId != null && user != null) {
			if (user.getRole() == UserRole.ADMIN) {
				return "admin/admin_home";// display admin home page.
			} else if (user.getRole() == UserRole.CUSTOMER) {
				return "user/user_home"; // display user home page.
			}
		}
		return "redirect:/login"; // return to login page again.
	}

	/**
	 * Handles the logout request and invalidates the session.
	 *
	 * @param session The HttpSession object.
	 * @param model   The model object to pass data to the view.
	 * @return The view name for the login page.
	 */
	@RequestMapping(LoginEndpoints.LOGOUT)
	public String logout(HttpSession session, Map<String, Object> model) {
		// setting logout message.
		model.put("logoutMessage", "Logout Success, Login again below if needed");

		session.invalidate();

		return "login";
	}
}
