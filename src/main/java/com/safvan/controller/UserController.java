package com.safvan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@GetMapping(value = { "/", "/home" })
	public String showHomePage() {
		return "user/user_home";
	}
}
