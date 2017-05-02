package com.tickettracker.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tickettracker.boot.TicketConfiguration;
import com.tickettracker.domain.User;

/***
 * 
 * @author Pooja Mantri
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketConfiguration.class)
public class UserDAOTest {
	@Inject
	UserDAO dao;

	@Test
	public void shouldNotBeNull() {
		assertNotNull(dao);
	}

	@Test
	public void shouldCreateUser() {
		User user = new User("Pooja Mantri", "mantripooja93@gmail.com", "CEO");
		dao.merge(user);
		assertEquals(dao.getCount(), Long.valueOf(3));
	}

	@Test
	public void shouldUpdateUser() {
		User user = dao.findById(2);
		user.setType("CTO");
		dao.merge(user);
		assertEquals(user.getType(), "CTO");
	}

	@Test
	public void shouldDeleteUser() {
		User user = dao.findById(2);
		dao.delete(user);
		assertEquals(dao.getCount(), Long.valueOf(2));
	}

	@Test
	public void shouldNotAllowInvalidUsername() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		User user = new User("P", "mantripooja93@gmail.com", "CEOddd");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertEquals(1, violations.size());
	}

}
