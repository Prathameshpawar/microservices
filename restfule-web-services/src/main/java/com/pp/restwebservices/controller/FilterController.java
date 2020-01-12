package com.pp.restwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.restwebservices.pojo.FilterBean;

@RestController
public class FilterController {

	
	@GetMapping("/filterBean")
	public FilterBean getFilterBean() {
		return new FilterBean("value1","value2","value3");
	}
}
