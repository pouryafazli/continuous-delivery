package com.example.continuousdelivery;

import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepository;
	
	
		
	@GetMapping("/customers")
	Collection<Customer> agetCustomer(){
		return this.customerRepository.findAll();
	}
	
	@GetMapping(path= "/customers/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Customer getCustomerById(@PathVariable Long id){
		return this.customerRepository.findById(id).get();
	}
}
