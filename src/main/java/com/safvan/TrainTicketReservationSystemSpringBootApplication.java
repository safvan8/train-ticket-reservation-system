package com.safvan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * The main class of the Train Ticket Reservation System Spring Boot
 * application. It is responsible for starting the application.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
public class TrainTicketReservationSystemSpringBootApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TrainTicketReservationSystemSpringBootApplication.class, args);
	}

}