package com.safvan.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Before("within(com.safvan.controller.*)")
    public void logControllerMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.debug("Entering method: {}.{}", className, methodName);
    }

    @Before("within(com.safvan.service.*)")
    public void logServiceMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.debug("Entering method: {}.{}", className, methodName);
    }
    
    @Before("within(com.safvan.repository.*)")
    public void logRepositoryMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.debug("Entering method: {}.{}", className, methodName);
    }


    @AfterReturning(pointcut = "execution(* com.safvan..*(..)) && !within(com.safvan.advices.LoggingAdvice) ", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.debug("Exiting method: {}.{}", className, methodName);
        logger.debug("Returned value: {}", result);
    }

    @AfterThrowing(pointcut = "execution(* com.safvan..*(..)) && !within(com.safvan.advices.LoggingAdvice)", throwing = "ex")
    public void logMethodException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.error("Exception in method: {}.{}", className, methodName);
        logger.error("Exception message: {}", ex.getMessage());
    }
}
