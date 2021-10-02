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

    private static Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);


    //
    // aop advices
    //

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        LOG.debug("{} ms execution time for {}", executionTime, joinPoint.getSignature());
        return proceed;
    }

    @Before("execution(* com.example.pizza..*.create*(..))")
    public void logCreate(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length == 1) {
            // log output occurs twice, needs to be fixed: https://stackoverflow.com/questions/11516092/spring-aop-advice-called-twice
            LOG.debug("Creating {}", joinPoint.getArgs()[0]);
        }
    }
}
