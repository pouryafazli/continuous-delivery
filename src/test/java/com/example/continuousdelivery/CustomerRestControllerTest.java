package com.example.continuousdelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CustomerRestControllerTest {
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Autowired
	private MockMvc mockMVC;

	@Test
	public void CustomerByIdShouldreturnJsonCustomer() throws Exception {
		
		
		Customer c = new Customer(1L,"","","");
		Optional<Customer> t =  Optional.ofNullable(c);
		Mockito.when(this.customerRepository.findById(1L))
			.thenReturn(t);
		
		this.mockMVC.perform(MockMvcRequestBuilders.get("/customers/1/"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(MockMvcResultMatchers.jsonPath("@.id").value(1L))
			;
	}
	
	@Test
	public void CustomersShouldreturnJsonCustomer() throws Exception {
		
		Customer c = new Customer(1L,"","","");
		List<Customer> list = new ArrayList<>();
		list.add(c);
		
		Mockito.when(this.customerRepository.findAll())
		.thenReturn(list );
		this.mockMVC.perform(MockMvcRequestBuilders.get("/customers"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
			;
	}
}
