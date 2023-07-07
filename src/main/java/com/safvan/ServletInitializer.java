package com.safvan;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The ServletInitializer class extends the SpringBootServletInitializer class
 * to configure the application when deploying it as a WAR file in a servlet
 * container.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Configures the application builder with the main application class.
	 *
	 * @param application The Spring application builder.
	 * @return The configured Spring application builder.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TrainTicketReservationSystemSpringBootApplication.class);
	}

}
