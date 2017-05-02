package com.tickettracker.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tickettracker.boot.TicketConfiguration;
import com.tickettracker.domain.Ticket;
import com.tickettracker.domain.User;

/**
 * 
 * @author Pooja Mantri
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketConfiguration.class)
public class TicketDAOTest {

	@Inject
	UserDAO userDAO;

	@Inject
	TicketDAO ticketDAO;

	@Test
	public void shouldNotBeNull() {
		assertNotNull(userDAO);
		assertNotNull(ticketDAO);
	}

	@Test
	public void shouldCreateTicket() {
		User user = userDAO.findById(4);
		Ticket ticket = new Ticket();
		ticket.setAssignedTo(user);
		ticket.setCreatedBy(user);
		ticket.setCustomer(user);
		ticket.setDescription("Better solve this one also !");
		ticketDAO.merge(ticket);
		assertEquals(ticketDAO.getCount(), Long.valueOf(4));
	}

	@Test
	public void shouldUpdateTicket() {
		Ticket ticket = ticketDAO.findById(1);
		ticket.setArea("Mumbai");
		ticketDAO.merge(ticket);
		Ticket ticket1 = ticketDAO.findById(1);
		assertEquals(ticket1.getArea(), "Mumbai");
	}

	@Test
	public void shouldDeleteUser() {
		Ticket ticket = ticketDAO.findById(1);
		ticketDAO.delete(ticket);
		assertEquals(ticketDAO.getCount(), Long.valueOf(1));
	}

	@Test
	public void shouldFetchUserForTicket() {
		Ticket ticket = ticketDAO.findById(1);
		User assignedTo = ticket.getAssignedTo();
		assertEquals(assignedTo.getName(), "Pooja Mantri");
	}

	@Test
	public void shouldFindTicketsAssignedToUser() {
		User user = userDAO.findById(4);
		List<Ticket> ticketsAssigned = ticketDAO
				.findTicketsAssignedToUser(user);
		assertEquals(ticketsAssigned.size(), 2);
	}

	@Test
	public void findTicketsCreatedByUser() {
		User user = userDAO.findById(4);
		List<Ticket> ticketsCreated = ticketDAO.findTicketsCreatedByUser(user);
		assertEquals(ticketsCreated.size(), 2);
	}

	@Test
	public void findTicketsRelatedToCustomer() {
		User user = userDAO.findById(4);
		List<Ticket> ticketsRaisedByCustomer = ticketDAO
				.findTicketsRelatedToCustomer(user);
		assertEquals(ticketsRaisedByCustomer.size(), 2);
	}

}
