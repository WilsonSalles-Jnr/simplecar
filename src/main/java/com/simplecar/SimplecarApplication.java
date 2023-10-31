package com.simplecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.simplecar.utils.ModelMapperConverter;

@SpringBootApplication
public class SimplecarApplication {
	
	@Bean
	public ModelMapperConverter modelMapper() {
		return new ModelMapperConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(SimplecarApplication.class, args);
	}

}
