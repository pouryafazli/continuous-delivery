package com.example.continuousdelivery;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class CustomerTest {

	private  Validator validator;
	
	@Before
	public void before() throws Throwable{
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		validator = localValidatorFactoryBean.getValidator();
		
	}
	
	@Test
	public void newInstanceWithValidValueShouldRetuenArecord() {
		Customer customer = new Customer(1L,null,null,"emailemail.com");
		Assert.assertNotNull(customer);
		Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
		BDDAssertions.then(constraintViolations.size()).isEqualTo(3);
	}
}
