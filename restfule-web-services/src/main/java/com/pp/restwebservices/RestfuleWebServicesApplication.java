package com.pp.restwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfuleWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfuleWebServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		// SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	// below is config in properties file.
//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource() {
//		ResourceBundleMessageSource bundleMessageSource  = new ResourceBundleMessageSource();
//		bundleMessageSource.setBasename("messages");
//		return bundleMessageSource;
//	}

}
