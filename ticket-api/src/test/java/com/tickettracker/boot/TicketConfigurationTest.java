package com.tickettracker.boot;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Pooja Mantri
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketConfiguration.class)
public class TicketConfigurationTest {

	@Autowired
	TicketConfiguration tc;

	@Test
	public void shouldNotBeNull() {
		assertNotNull(tc);
	}

}
