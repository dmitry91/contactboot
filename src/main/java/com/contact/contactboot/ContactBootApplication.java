package com.contact.contactboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ContactBootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ContactBootApplication.class, args).registerShutdownHook();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ContactBootApplication.class);
	}
}
