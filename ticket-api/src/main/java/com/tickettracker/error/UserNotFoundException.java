package com.tickettracker.error;

/**
 * @author Pooja Mantri
 *
 */
public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserNotFoundException(int userId) {
		super();
		this.userId = userId;
	}

}
