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

	public Ticket getTicket(int id) {
		return ticketDAO.findById(id);
	}

	public List<Ticket> getAllTickets() {
		return ticketDAO.findAll();
	}

	public void createTicket(Ticket ticket) {
		ticketDAO.merge(ticket);
	}

	public void updateTicket(Ticket ticket) {
		ticketDAO.merge(ticket);
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
