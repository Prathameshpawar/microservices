package com.pp.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pp.microservices.controller.limitservice.Configuration;
import com.pp.microservices.controller.limitservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}

	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveMethod")
	public LimitConfiguration retrieveLimitsInfo() {
		throw new RuntimeException("Not available");
	}
	
	public LimitConfiguration fallbackRetrieveMethod() {
		return new LimitConfiguration(50, 500);
	}
}