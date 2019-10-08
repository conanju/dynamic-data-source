package com.dynamic.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
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
public class Message1 implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }


    @Around("com.dynamic.aop.MessagePointCuts.message()")
    public void aroundMessageTwo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around messageTwo in .......");
        proceedingJoinPoint.proceed();
        log.info("Around messageTwo .......");
    }

    @Before("com.dynamic.aop.MessagePointCuts.message()")
    public void beforeMessageTwo(JoinPoint joinPoint) {

        log.info("Before messageTwo .......");
    }
}
