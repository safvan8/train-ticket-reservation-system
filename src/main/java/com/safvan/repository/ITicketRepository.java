package com.safvan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safvan.beans.Ticket;

public interface ITicketRepository extends PagingAndSortingRepository<Ticket, Long> {

}
