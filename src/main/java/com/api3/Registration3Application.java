package com.api3;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Registration3Application {

	public static void main(String[] args) {
		SpringApplication.run(Registration3Application.class, args);
	}
@Bean
	public ModelMapper modelmp(){
		return new ModelMapper();
}
}
