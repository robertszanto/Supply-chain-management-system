package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    @Around("@annotation(com.example.demo.aspect.Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnedObj = joinPoint.proceed();
        String methodName = joinPoint.getSignature().getName();
        log.info("Method - " + methodName + " - was executed with: " + returnedObj.toString());
        return returnedObj;
    }
}
