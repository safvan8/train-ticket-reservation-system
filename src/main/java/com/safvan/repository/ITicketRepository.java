package com.safvan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safvan.beans.Ticket;

/**
 * ITicketRepository interface.
 * 
 * This interface extends the PagingAndSortingRepository interface provided by
 * Spring Data. It allows performing CRUD ,Sorting,paging operations on the
 * Ticket entity.
 * 
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
public interface ITicketRepository extends PagingAndSortingRepository<Ticket, Long> {
	// Add custom methods if needed
}
