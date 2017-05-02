package com.tickettracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Pooja Mantri
 *
 */
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int id;

	@NotNull
	@Size(min = 2, max = 255, message = "Name is required, maximum 255 characters.")
	private String name;
	private String email;
	private String type;

	public User() {

	}

	public User(String name, String email, String type) {
		super();
		this.name = name;
		this.email = email;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// // TODO try cascade
	// @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.PERSIST)
	// public Collection<Ticket> ticketsAssigned = new ArrayList<Ticket>();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User person = (User) o;
		if (!name.equals(person.name))
			return false;
		if (!email.equals(person.email))
			return false;
		if (!type.equals(person.type))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + type.hashCode();
		return result;
	}
}
