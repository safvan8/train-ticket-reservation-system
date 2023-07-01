package com.safvan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.safvan.interceptor.SessionInterceptor;

public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private SessionInterceptor sessionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
	}
}
