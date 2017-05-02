package com.tickettracker.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tickettracker.domain.Ticket;
import com.tickettracker.domain.User;

/**
 * @author Pooja Mantri
 *
 */
@Repository
public class TicketDAOImpl extends GenericDAOImpl<Ticket, Integer> implements
		TicketDAO {

	public TicketDAOImpl() {
		super(Ticket.class);
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findTicketsAssignedToUser(User user) {
		return em.createNamedQuery("findTicketsAssignedToUser")
				.setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findTicketsCreatedByUser(User user) {
		return em.createNamedQuery("findTicketsCreatedByUser")
				.setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findTicketsRelatedToCustomer(User user) {
		return em.createNamedQuery("findTicketsRelatedToCustomer")
				.setParameter("user", user).getResultList();
	}

}