package com.safvan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.safvan.beans.User;
import com.safvan.constants.UserRole;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String showHome(HttpSession session) {
		
		System.out.println("HomeController.showHome()");
		User user = (User) session.getAttribute("user");

		
		if (user != null) {
			if (user.getRole().equals(UserRole.ADMIN)) {
				return "admin/admin_home";
			} else if (user.getRole() == UserRole.CUSTOMER) {
				return "user/user_home";
			}
		}
		return "redirect:/login";
	}
}
