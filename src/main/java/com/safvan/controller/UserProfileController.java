package com.safvan.controller;

import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safvan.beans.User;
import com.safvan.constants.UserRole;
import com.safvan.service.ILoginManagementService;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {

	@Autowired
	private ILoginManagementService loginManagementService;

	@GetMapping("/view")
	public String showUserProfile(HttpSession session, Map<String, Object> model) {

		String sessionId = (String) session.getAttribute("sessionId");
		System.out.println(sessionId);

		User user = null;
		// if sessionId is not null , find user by using that sessionId
		if (sessionId != null) {
			user = loginManagementService.getUserbySessionId(sessionId);
		}

		System.out.println("============" + user);
		// Additional code to retrieve the user based on the session ID from the
		// database or cache
		if (sessionId != null && user != null) {
			model.put("user", user);
			byte[] imageByteArray = user.getUserProfile().getImage();
			model.put("userImage", Base64.getEncoder().encodeToString(imageByteArray));
		}
		return "display_profile";

	}

}
