//NOT working for me
//TODO use it in ticket repo

@org.hibernate.annotations.NamedQueries({
@org.hibernate.annotations.NamedQuery(
name = "findTicketsAssignedToUser",
query = "select t from Ticket t where t.assignedTo=:user",
timeout = 60
)
,
@org.hibernate.annotations.NamedQuery(
name = "findTicketsCreatedByUser",
query = "select t from ticket t where created_by=:userId"
),
@org.hibernate.annotations.NamedQuery(
name = "findTicketsRelatedToCustomer",
query = "select t from ticket t where customer=:userId"
)
})


package com.tickettracker.dao;