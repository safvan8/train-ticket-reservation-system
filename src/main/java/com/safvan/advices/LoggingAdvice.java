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
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
@Aspect
@Component
public class LoggingAdvice {
    
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    /**
	 * Log controller method entry.
	 *
	 * @param joinPoint the join point
	 */
    @Before("within(com.safvan.controller.*)")
    public void logControllerMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.debug("Entering method: {}.{}", className, methodName);
    }

    /**
	 * Log service method entry.
	 *
	 * @param joinPoint the join point
	 */
    @Before("within(com.safvan.service.*)")
    public void logServiceMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.debug("Entering method: {}.{}", className, methodName);
    }
    
    /**
	 * Log repository method entry.
	 *
	 * @param joinPoint the join point
	 */
    @Before("within(com.safvan.repository.*)")
    public void logRepositoryMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.debug("Entering method: {}.{}", className, methodName);
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
        logger.debug("Exiting method: {}.{}", className, methodName);
        logger.debug("Returned value: {}", result);
    }

    /**
	 * Log method exception.
	 *
	 * @param joinPoint the join point
	 * @param ex        the ex
	 */
    @AfterThrowing(pointcut = "execution(* com.safvan..*(..)) && !within(com.safvan.advices.LoggingAdvice)", throwing = "ex")
    public void logMethodException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.error("Exception in method: {}.{}", className, methodName);
        logger.error("Exception message: {}", ex.getMessage());
    }
}
