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

	public User findUser(int id) {
		return userDAO.findById(id);
	}

	public List<User> findAll() {
		return userDAO.findAll();
	}

	public User merge(User user) {
		return userDAO.merge(user);
	}

	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	public void deleteUser(int id) {
		userDAO.deleteById(id);
	}
}
