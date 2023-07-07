package com.safvan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.safvan.interceptor.SessionInterceptor;

/**
 * WebMvcConfig class is responsible for configuring web MVC-related settings in
 * the application.
 *
 * It registers the SessionInterceptor as an interceptor to be applied for
 * specific URL patterns.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * Configures the interceptors to be applied for specific URL patterns.
	 *
	 * In this case, the SessionInterceptor is registered to be applied for URL
	 * patterns "/admin/**", "/user/**", and "/userProfile/**".
	 *
	 * The SessionInterceptor is responsible for checking if the user session is
	 * still active. If the session has expired, the interceptor redirects the user
	 * to the login page, ensuring that only authenticated users can perform
	 * activities within the session.
	 *
	 * @param registry the InterceptorRegistry to register the interceptors
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		/**
		 * By adding the SessionInterceptor to the registry, it becomes part of the
		 * request processing pipeline and will be invoked for the specified URL
		 * patterns. This allows the interceptor to intercept and handle requests before
		 * they reach the controller, providing session management and security
		 * functionality.
		 */
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/admin/**", "/user/**", "/userProfile/**");
	}
}
