package com.tickettracker.error;

/**
 * @author Pooja Mantri
 *
 */
public class TicketNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ticketId;

	public TicketNotFoundException(int ticketId) {
		super();
		this.ticketId = ticketId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
}
