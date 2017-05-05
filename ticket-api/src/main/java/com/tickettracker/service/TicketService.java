package com.tickettracker.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tickettracker.dao.TicketDAO;
import com.tickettracker.dao.UserDAO;
import com.tickettracker.domain.Ticket;
import com.tickettracker.domain.User;

/**
 * @author Pooja Mantri
 *
 */
@Service
public class TicketService {

	@Inject
	UserDAO userDAO;

	@Inject
	TicketDAO ticketDAO;

	public Ticket findTicket(int id) {
		return ticketDAO.findById(id);
	}

	public List<Ticket> findAll() {
		return ticketDAO.findAll();
	}

	public Ticket merge(Ticket ticket) {
		return ticketDAO.merge(ticket);
	}

	public void deleteTicket(Ticket ticket) {
		ticketDAO.delete(ticket);
	}

	public void deleteTicket(int id) {
		ticketDAO.deleteById(id);
	}

	public List<Ticket> getAssignedTickets(User user) {
		return ticketDAO.findTicketsAssignedToUser(user);
	}

	public List<Ticket> getCreatedTickets(User user) {
		return ticketDAO.findTicketsCreatedByUser(user);
	}

	public List<Ticket> getCustomerTickets(User user) {
		return ticketDAO.findTicketsRelatedToCustomer(user);
	}
}
