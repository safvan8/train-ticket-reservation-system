package com.safvan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.safvan.beans.User;
import com.safvan.service.IUserManagementService;
import com.safvan.util.FileUploadUtils;
import com.safvan.util.UserUtils;

/**
 * The RegistrationController handles the registration of new users. It provides
 * endpoints for displaying the registration form and processing the
 * registration data.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/appUsers")
public class RegistrationController {

	@Autowired
	private IUserManagementService userManagementService;

	/**
	 * Displays the registration form for new users.
	 *
	 * @return The view name for the registration form.
	 */
	@GetMapping("/register")
	public String showRegistarionForm() {

		return "register_user";
	}

	/**
	 * Registers a new customer based on the provided registration data.
	 *
	 * @param firstName   The first name of the user.
	 * @param lastName    The last name of the user.
	 * @param username    The username of the user.
	 * @param password    The password of the user.
	 * @param address     The address of the user.
	 * @param phoneNumber The phone number of the user.
	 * @param image       The profile image of the user (optional).
	 * @param model       The model object to pass data to the view.
	 * @return The view name for the user registration success page.
	 */

	@PostMapping("/confirmRegistration/customer")
	public String registerNewCustomer(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("address") String address,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(name = "image", required = false) MultipartFile image, Map<String, Object> model) {

		System.out.println("Heloooo");
		// Convert the image to byte array
		byte[] imageBytes = FileUploadUtils.convertToByteArray(image);

		// Create a User object with the registration data
		User user = UserUtils.createUser(username, password, firstName, lastName, address, phoneNumber, imageBytes);

		// Register the new customer
		User userRegistrationResult = userManagementService.registerNewCustomer(user);

		// Pass the registration result to the view
		model.put("userRegResult", userRegistrationResult);

		return "user_registration_success";
	}
}
