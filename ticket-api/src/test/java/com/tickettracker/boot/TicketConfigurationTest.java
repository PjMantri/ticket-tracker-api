package com.tickettracker.boot;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tickettracker.service.TicketService;
import com.tickettracker.service.UserService;

/**
 * 
 * @author Pooja Mantri
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketConfiguration.class)
public class TicketConfigurationTest {

	@Autowired
	TicketConfiguration config;

	@Autowired
	TicketService ticketService;

	@Autowired
	UserService userService;

	@Test
	public void shouldNotBeNull() {
		assertNotNull(config);
		assertNotNull(ticketService);
		assertNotNull(userService);
	}

}
