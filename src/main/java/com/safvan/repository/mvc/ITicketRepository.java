package com.safvan.repository.mvc;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safvan.beans.Ticket;
import com.safvan.beans.User;

/**
 * ITicketRepository interface.
 * 
 * This interface extends the PagingAndSortingRepository interface provided by
 * Spring Data. It allows performing CRUD ,Sorting,paging operations on the
 * Ticket entity.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 * 
 */
public interface ITicketRepository extends PagingAndSortingRepository<Ticket, String> {
	/**
	 * Find all tickets booked by a user (Customer) and return as a list.
	 * 
	 * @param user the user object representing the customer.
	 * @return a List of tickets booked by the user.
	 */
	public List<Ticket> findByUser(User user);

}
