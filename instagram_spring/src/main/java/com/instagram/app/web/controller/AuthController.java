package com.instagram.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.instagram.app.web.dto.auth.SignupRequestDto;

@Controller
public class AuthController {
	@RequestMapping(value = "/auth/signup", method = RequestMethod.GET)
	public String getSignup(SignupRequestDto signupRequestDto) {
		
		System.out.println("phoneOrEmail: " + signupRequestDto.getPhoneOrEmail());
		System.out.println("name: " + signupRequestDto.getName());
		System.out.println("username: " + signupRequestDto.getUsername());
		System.out.println("password: " + signupRequestDto.getPassword());
		
		return "auth/signup";
	}
}
