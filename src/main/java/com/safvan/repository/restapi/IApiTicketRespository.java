package com.safvan.repository.restapi;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safvan.beans.restapi.ApiTicket;

public interface IApiTicketRespository extends PagingAndSortingRepository<ApiTicket, String> {

}
