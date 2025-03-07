package com.exampleapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExampleapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleapiApplication.class, args);
	}

	@Bean
	public ModelMapper getmodelMapper(){
		return new ModelMapper();
	}
}
