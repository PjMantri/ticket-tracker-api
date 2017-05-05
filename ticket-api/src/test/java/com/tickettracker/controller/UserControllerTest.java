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
import com.tickettracker.domain.User;
import com.tickettracker.service.UserService;

/**
 * @author Pooja Mantri
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketConfiguration.class)
public class UserControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private UserController userController;

	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void shouldCreateUser() throws Exception {
		User user = new User("Phoebe Buffay", "phoebe.buffay@gmail.com",
				"Actress");
		mockMvc.perform(
				post("/user").contentType(MediaType.APPLICATION_JSON).content(
						json(user))).andExpect(
				jsonPath("$.name").value("Phoebe Buffay"));
	}

	@Test
	public void shouldDeleteUser() throws Exception {
		mockMvc.perform(
				delete("/user/{id}", 7).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFetchUserById() throws Exception {
		mockMvc.perform(get("/user/2")).andExpect(
				jsonPath("$.name").value("Pooja Mantri"));
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o,
				MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	@Test
	public void shouldFetchAllUsers() throws Exception {
		mockMvc.perform(get("/user")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(5)))
				.andExpect(jsonPath("$[0].id", is(2)));
	}
}