package com.safvan.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safvan.beans.User;
import com.safvan.constants.UserRole;
import com.safvan.service.IUserService;

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		User user = userService.authenticateUser(username, password);
		System.out.println("LoginController.login()");
		if (user != null) {
			System.out.println("LoginController.login(888888888888888888888888)");
			String sessionId = UUID.randomUUID().toString(); // Generate a unique session ID
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("user", user);
			// Additional code to store the session ID and associate it with the user in the
			// database or a cache
			return "redirect:/home";
		}
		return "redirect:/login?error";
	}

	
	@GetMapping("/home")
	public String showHome(HttpSession session) {
		System.out.println("HomeController.showHome()");
		String sessionId = (String) session.getAttribute("sessionId");
		System.out.println(sessionId);
		User user = new User();
		user.setRole(UserRole.CUSTOMER);
		// Additional code to retrieve the user based on the session ID from the
		// database or cache
		if (sessionId != null && user != null) {
			if (user.getRole() == UserRole.ADMIN) {
				System.out.println("LoginController.showHome6666666666666666666()");
				return "admin/admin_home";
			} else if (user.getRole() == UserRole.CUSTOMER) {
				return "user/user_home";
			}
		}
		return "redirect:/login";
	}

}
