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
import com.safvan.service.ILoginManagementService;

@Controller
public class LoginController {

	@Autowired
	private ILoginManagementService loginManagementService;

	@GetMapping(value = { "/login", "/" })
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		User user = loginManagementService.authenticateUser(username, password);

		System.out.println("LoginController.login()");

		if (user != null) {
			System.out.println("LoginController.login(888888888888888888888888)");
			String sessionId = UUID.randomUUID().toString(); // Generate a unique session ID
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(60);

			loginManagementService.storeUserSession(user.getUserId(), sessionId);
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

		User user = null;
		// if sessionId is not null , find user by using that sessionId
		if (sessionId != null) {
			user = loginManagementService.getUserbySessionId(sessionId);
		}

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

	@RequestMapping("/logout")
	public String logout(HttpSession session, Map<String, Object> model) {
		model.put("message", "Logout Success, Login again below if needed");

		session.invalidate();

		return "login";
	}

}
