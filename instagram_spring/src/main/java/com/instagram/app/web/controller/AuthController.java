package com.instagram.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instagram.app.service.AuthService;

@Controller
public class AuthController {
	
	@Autowired 
	private AuthService authService;
	
	
	@RequestMapping(value = "/auth/signup", method = RequestMethod.GET)
	public String getSignup() {
		return "auth/signup";
	}
	
	@ResponseBody
	@RequestMapping(value = "/auth/username/check", method = RequestMethod.GET)
	public boolean usernameCheck(String username) {
		return authService.checkUsername(username);
	}
}






