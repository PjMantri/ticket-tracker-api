package com.tickettracker.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tickettracker.dao.UserDAO;
import com.tickettracker.domain.User;

/**
 * @author Pooja Mantri
 *
 */
@Service
public class UserService {

	@Inject
	UserDAO userDAO;

	public User getUser(int id) {
		return userDAO.findById(id);
	}

	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	public void createUser(User user) {
		userDAO.merge(user);
	}

	public void updateUser(User user) {
		userDAO.merge(user);
	}

	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	public void deleteUser(int id) {
		userDAO.deleteById(id);
	}
}
