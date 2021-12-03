package com.example.pizza.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //
    // constants
    //

    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);


    //
    // aop advices
    //

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // track time before and after method invocation
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        // log it
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        logger.debug("{} ms execution time for {}", executionTime, joinPoint.getSignature());
        return proceed;
    }


    @Before("execution(* com.example.pizza..*.create*(..))")
    public void logCreate(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length == 1) {
            Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
            logger.debug("Creating {}", joinPoint.getArgs()[0]);
        }
    }
}
