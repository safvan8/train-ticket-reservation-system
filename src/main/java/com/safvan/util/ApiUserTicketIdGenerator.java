package com.safvan.util;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * Custom identifier generator to generate unique ticket IDs for API endpoint users.
 */
@Component
public class ApiUserTicketIdGenerator implements IdentifierGenerator {

	private static final String PREFIX = "TP-";
	private static long sequence = 1;

	/**
	 * Generates a unique ticket ID for a API endpoint user.
	 *
	 * @param session The current Hibernate session.
	 * @param object  The object for which the identifier is being generated.
	 * @return The generated unique ticket ID.
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		return PREFIX + String.format("%06d", sequence++);
	}
}
