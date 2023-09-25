package com.safvan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 *
 */
@Configuration
public class AppConfig {

	/**
	 * Creates and configures a DelegatingPasswordEncoder bean with
	 * BCryptPasswordEncoder as the default encoder.
	 *
	 * @return The configured DelegatingPasswordEncoder bean.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {

		// Create an instance of BCryptPasswordEncoder as the default encoder
		PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();

		// Create a DelegatingPasswordEncoder instance
		DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories
				.createDelegatingPasswordEncoder();

		// Set the default encoder for matches
		delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);

		return delegatingPasswordEncoder;
	}
}
