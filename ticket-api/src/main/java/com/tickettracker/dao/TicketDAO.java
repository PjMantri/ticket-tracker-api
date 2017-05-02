package com.tickettracker.dao;

import java.util.List;

import com.tickettracker.domain.Ticket;
import com.tickettracker.domain.User;

/**
 * @author Pooja Mantri
 *
 */
public interface TicketDAO extends GenericDAO<Ticket, Integer> {

	List<Ticket> findTicketsAssignedToUser(User user);

	List<Ticket> findTicketsCreatedByUser(User user);

	List<Ticket> findTicketsRelatedToCustomer(User user);
}