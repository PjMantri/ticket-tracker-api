package com.tickettracker.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tickettracker.boot.TicketConfiguration;
import com.tickettracker.domain.Ticket;
import com.tickettracker.service.TicketService;

/**
 * @author Pooja Mantri
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketConfiguration.class)
public class TicketControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private TicketController ticketController;

	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
	}

	@Test
	public void shouldCreateTicket() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setStatus("Open");
		mockMvc.perform(
				post("/ticket").contentType(MediaType.APPLICATION_JSON)
						.content(json(ticket))).andExpect(
				jsonPath("$.status").value("Open"));
	}

	@Test
	public void shouldUpdateTicket() throws Exception {
		Ticket ticket = ticketService.findTicket(6);
		ticket.setStatus("Closed");
		mockMvc.perform(
				post("/ticket").contentType(MediaType.APPLICATION_JSON)
						.content(json(ticket))).andExpect(
				jsonPath("$.status").value("Closed"));
	}

	@Test
	public void shouldDeleteTicket() throws Exception {
		mockMvc.perform(
				delete("/ticket/{id}", 3).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void shouldFetchTicketById() throws Exception {
		mockMvc.perform(get("/ticket/1")).andExpect(
				jsonPath("$.area").value("Mumbai"));
	}

	@Test
	public void shouldFetchAllTickets() throws Exception {
		mockMvc.perform(get("/ticket")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].area", is("Mumbai")));
	}

	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o,
				MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}