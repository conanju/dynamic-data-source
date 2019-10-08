package com.dynamic.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Copyright
 *
 * @author conanju
 * @since 2019/9/26 11:10
 */
@Component
@Aspect
@Slf4j
public class Message2 {
    @Around("com.dynamic.aop.MessagePointCuts.message()")
    public void aroundMessageOne(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around messageOne .......in");
        proceedingJoinPoint.proceed();
        log.info("Around messageOne .......");
    }

    @Before("com.dynamic.aop.MessagePointCuts.message()")
    public void beforeMessageOne(JoinPoint joinPoint) {
        log.info("Before messageOne .......");
    }
}
