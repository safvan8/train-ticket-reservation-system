package com.safvan.util;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * Custom identifier generator to generate unique ticket IDs for local users.
 */
@Component
public class LocalUserTicketIdGenerator implements IdentifierGenerator {

	private static final String PREFIX = "L";
	private static long sequence = 1;

	/**
	 * Generates a unique ticket ID for a local user.
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
