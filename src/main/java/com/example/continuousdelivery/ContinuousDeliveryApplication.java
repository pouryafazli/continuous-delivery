package com.example.continuousdelivery;

import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ContinuousDeliveryApplication {

	@Bean
	ApplicationRunner init(CustomerRepository customerRepository) {
		return  args ->{
			Stream.of("a","b","c")
			.forEach(n -> customerRepository.save(new Customer(null,n,n,"email@usds.com")));
			customerRepository.findAll().forEach(System.out::println);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ContinuousDeliveryApplication.class, args);
	}

	
	
}




