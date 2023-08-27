package com.safvan.util;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * Custom identifier generator to generate unique ticket IDs for API endpoint
 * users.
 */
@Component
public class ApiUserTicketIdGenerator implements IdentifierGenerator {

	private static final String PREFIX = "A-";
	private static long counter = 1;

	/**
	 * Generates a unique ticket ID for a API endpoint user.
	 *
	 * @param session The current Hibernate session.
	 * @param object  The object for which the identifier is being generated.
	 * @return The generated unique ticket ID.
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		// Get the current timestamp in milliseconds
		long timestamp = System.currentTimeMillis();

		// Create a unique value by combining timestamp and counter using bitwise OR
		// Shifting the timestamp to the left by 20 bits to create space for the counter
		long uniqueValue = (timestamp << 20) | (counter & 0xFFFFF);

		// Increment the counter and wrap around if necessary
		counter = (counter + 1) & 0xFFFFF;

		// Format the unique value with the prefix and padding zeros
		return PREFIX + String.format("%010d", uniqueValue);
	}
}
