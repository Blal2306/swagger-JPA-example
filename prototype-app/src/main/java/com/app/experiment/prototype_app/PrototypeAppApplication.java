package com.app.experiment.prototype_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PrototypeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrototypeAppApplication.class, args);
		System.out.println("The application is up...");
	}
}

