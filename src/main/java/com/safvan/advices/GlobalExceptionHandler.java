//package com.safvan.advices;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(Exception.class)
//	public void handleAllExceptions(Exception e,HttpServletRequest request) {
//		
//		System.out.println(request.getRequestURI());
//		
//		e.printStackTrace();
//	}
//}
