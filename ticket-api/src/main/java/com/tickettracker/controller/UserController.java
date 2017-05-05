package com.tickettracker.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tickettracker.domain.User;
import com.tickettracker.error.Error;
import com.tickettracker.error.UserNotFoundException;
import com.tickettracker.service.UserService;

/**
 * @author Pooja Mantri
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<User> users() {
		return userService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<User> saveTicket(@RequestBody User user,
			UriComponentsBuilder uriComponentsBuilder) {
		User userCreated = userService.merge(user);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri = uriComponentsBuilder.path("/ticket/")
				.path(String.valueOf(userCreated.getId())).build().toUri();
		headers.setLocation(locationUri);

		ResponseEntity<User> responseEntity = new ResponseEntity<User>(
				userCreated, headers, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User userByID(@PathVariable int id) {
		User user = userService.findUser(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error userNotFound(UserNotFoundException e) {
		int userId = e.getUserId();
		return new Error(4, "User [" + userId + "] not found");
	}

}
