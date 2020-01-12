package com.pp.restwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.restwebservices.pojo.AuthenticationBean;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BasicAuthenticationController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/basicauth")
	public AuthenticationBean helloWorldBean() {
		return new AuthenticationBean("Basic authentication is done!!!");
	}
}
