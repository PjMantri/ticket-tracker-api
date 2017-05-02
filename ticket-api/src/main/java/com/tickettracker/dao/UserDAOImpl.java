package com.tickettracker.dao;

import org.springframework.stereotype.Repository;

import com.tickettracker.domain.User;

/**
 * @author Pooja Mantri
 *
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements
		UserDAO {
	public UserDAOImpl() {
		super(User.class);
	}
}