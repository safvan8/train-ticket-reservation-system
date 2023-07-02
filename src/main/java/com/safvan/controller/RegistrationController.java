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

@Controller
@RequestMapping("/appUsers")
public class RegistrationController {

	@Autowired
	private IUserManagementService userManagementService;

	@GetMapping("/register")
	public String showRegistarionForm() {

		return "register_user";
	}

	/**
	 * MultipartFile is a class provided by Spring Framework that represents an
	 * uploaded file. It allows you to handle file uploads in your controller
	 * methods.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param address
	 * @param phoneNumber
	 * @param image
	 * @return
	 */

	@PostMapping("/confirmRegistration/customer")
	public String registerNewCustomer(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("address") String address,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(name = "image", required = false) MultipartFile image,
			Map<String, Object> model) {

		System.out.println("Heloooo");

		byte[] imageBytes = FileUploadUtils.convertToByteArray(image);

		User user = UserUtils.createUser(username, password, firstName, lastName, address, phoneNumber, imageBytes);

		User userRegResult = userManagementService.registerNewCustomer(user);
		
		model.put("userRegResult", userRegResult);
		

		return "user_registration_success";
	}
}
