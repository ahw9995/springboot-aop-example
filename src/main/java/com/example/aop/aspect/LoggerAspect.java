package com.example.aop.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("execution(* com.example.aop.service.*.*(..))")
    public void onBeforeHandler(JoinPoint joinPoint) {
        logger.info("=========== Before handler =============");
        logger.info("method path: " + joinPoint.getSignature());
        logger.info("========================================");
    }

    @After("execution(* com.example.aop.service.*.*(..))")
    public void onAfterHandler(JoinPoint joinPoint) {
        logger.info("============ After handler =============");
        logger.info("method path: " + joinPoint.getSignature());
        logger.info("========================================");
    }

    @AfterReturning(pointcut = "execution(* com.example.aop.service.*.*(..))", returning = "str")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object str) {
        logger.info("============ After returning handler =============");
        logger.info("AfterReturning: " + str);
        logger.info("==================================================");
    }

    @Pointcut("execution(* com.example.service.*.*(..))")
    public void onPointcut(JoinPoint joinPoint) {
        logger.info("============ Point cut =============");
        logger.info("method path: " + joinPoint.getSignature());
        logger.info("====================================");
    }
}
