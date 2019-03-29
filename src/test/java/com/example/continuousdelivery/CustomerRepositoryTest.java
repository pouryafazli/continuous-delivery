package com.example.continuousdelivery;


import javax.validation.ConstraintViolationException;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired 
	private CustomerRepository customerRepository;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void saveShouldMapCorrectly() throws Exception{
		
		Customer customer = new Customer(null, "first", "last", "email@email.com");
		Customer saved = this.testEntityManager.persistAndFlush(customer);
		BDDAssertions.then(saved.getId()).isNotNull();
		BDDAssertions.then(saved.getFirstName()).isNotNull();
		BDDAssertions.then(saved.getFirstName()).isEqualToIgnoringCase("first");
		BDDAssertions.then(saved.getLastname()).isEqualToIgnoringCase("last");
		BDDAssertions.then(saved.getEmail()).isEqualToIgnoringCase("email@email.com");
		
	}
	
	@Test
	public void erepositorySaveShouldMapCorrectly() throws Exception{
		
		Customer customer = new Customer(null, "first", "last", "email@email.com");
		Customer saved = this.customerRepository.save(customer);
		BDDAssertions.then(saved.getId()).isNotNull();
		BDDAssertions.then(saved.getFirstName()).isNotNull();
		BDDAssertions.then(saved.getFirstName()).isEqualToIgnoringCase("first");
		BDDAssertions.then(saved.getLastname()).isEqualToIgnoringCase("last");
		BDDAssertions.then(saved.getEmail()).isEqualToIgnoringCase("email@email.com");
		
	}
	
	@Test
	public void catchException() {
		this.expectedException.expect(ConstraintViolationException.class);
		testEntityManager.persistAndFlush(new Customer(null,null,null,"emailusds.com"));
		
	}
	
	
}
