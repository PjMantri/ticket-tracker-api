package com.tickettracker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author Pooja Mantri
 *
 */
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID")
	private int id;
	private String status;
	private String area;
	private String description;

//	@org.hibernate.annotations.Formula("substr(description, 1, 12)")
//	@Transient
//	protected String shortDescription;

	@ManyToOne(cascade = {CascadeType.ALL})	
	@JoinColumn(name = "customer", referencedColumnName = "USER_ID")
	private User customer;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "created_by", referencedColumnName = "USER_ID")
	private User createdBy;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "assigned_to", referencedColumnName = "USER_ID")
	private User assignedTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public String getShortDescription() {
//		return description;
//	}
//
//	public void setShortDescription(String shortDescription) {
//		this.shortDescription = shortDescription;
//	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}
}
