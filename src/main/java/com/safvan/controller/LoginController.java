package com.safvan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safvan.beans.User;
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
            session.setAttribute("user", user);
            return "redirect:/home";
        }
        return "redirect:/login?error";
    }
}
