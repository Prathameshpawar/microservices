package com.pp.restwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pp.restwebservices.pojo.HelloWorldBean;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class HellowWorldController {

	@Autowired
	private MessageSource messageSource;

	// @RequestMapping(path="/hello-world",method=RequestMethod.GET)
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hey Hello World,How are you?";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world Bean ,Process data!!!");
	}

	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello world Bean Path Variable :!!!" + name);
	}

	// internationalization

//	@GetMapping("/hello-world-i18")
//	public String helloWorldI18(@RequestHeader(name="Accept-Language",required = false) Locale locale) {
//		return messageSource.getMessage("good.morning.message",null, locale);
//	}

	// without Accept-Language configuration
	@GetMapping("/hello-world-i18")
	public String helloWorldI18() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}
