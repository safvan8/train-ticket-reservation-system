package com.safvan.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * Class: LoggingAdvice
 * 
 * This Class represents a LoggingAdvice object.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Aspect
@Component
public class LoggingAdvice {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);

	/**
	 * Log controller method entry.
	 *
	 * @param joinPoint the join point
	 */
	@Before("within(com.safvan.controller.*)")
	public void logControllerMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		LOGGER.debug("Entering method: {}.{}", className, methodName);
	}

	/**
	 * Log service method entry.
	 *
	 * @param joinPoint the join point
	 */
	@Before("within(com.safvan.service.mvc.*)")
	public void logServiceMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		LOGGER.debug("Entering method: {}.{}", className, methodName);
	}

	/**
	 * Log repository method entry.
	 *
	 * @param joinPoint the join point
	 */
	@Before("within(com.safvan.repository.mvc.*)")
	public void logRepositoryMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		LOGGER.debug("Entering method: {}.{}", className, methodName);
	}

	/**
	 * Log method exit.
	 *
	 * @param joinPoint the join point
	 * @param result    the result
	 */
	@AfterReturning(pointcut = "execution(* com.safvan..*(..)) && !within(com.safvan.advices.LoggingAdvice) ", returning = "result")
	public void logMethodExit(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		LOGGER.debug("Exiting method: {}.{}", className, methodName);
		LOGGER.debug("Returned value: {}", result);
	}

	/**
	 * Log method exception. for general execption logging
	 *
	 * @param joinPoint the join point
	 * @param ex        the ex
	 */
	@AfterThrowing(pointcut = "execution(* com.safvan..*(..)) && !within(com.safvan.advices.LoggingAdvice)", throwing = "ex")
	public void logMethodException(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		LOGGER.error("Exception in method: {}.{}", className, methodName);
		LOGGER.error("Exception message: {}", ex.getMessage());
	}
}
