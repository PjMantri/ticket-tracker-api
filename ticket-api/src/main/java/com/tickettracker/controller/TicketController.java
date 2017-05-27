package com.tickettracker.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tickettracker.domain.Ticket;
import com.tickettracker.error.Error;
import com.tickettracker.error.TicketNotFoundException;
import com.tickettracker.service.TicketService;
import com.tickettracker.service.UserService;

/**
 * @author Pooja Mantri
 *
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@Autowired
	UserService userService;

	@CrossOrigin("*")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Ticket> tickets() {
		return ticketService.findAll();
	}

	@CrossOrigin("*")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket,
			UriComponentsBuilder uriComponentsBuilder) {
		Ticket ticketCreated = ticketService.merge(ticket);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri = uriComponentsBuilder.path("/ticket/")
				.path(String.valueOf(ticketCreated.getId())).build().toUri();
		headers.setLocation(locationUri);

		ResponseEntity<Ticket> responseEntity = new ResponseEntity<Ticket>(
				ticketCreated, headers, HttpStatus.CREATED);
		return responseEntity;
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Ticket ticketByID(@PathVariable int id) {
		Ticket ticket = ticketService.findTicket(id);
		if (ticket == null) {
			throw new TicketNotFoundException(id);
		}
		return ticket;
	}

	@ExceptionHandler(TicketNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error ticketNotFound(TicketNotFoundException e) {
		int ticketId = e.getTicketId();
		return new Error(4, "Ticket [" + ticketId + "] not found");
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteTicket(@PathVariable int id) {
		ticketService.deleteTicket(id);
	}

}
