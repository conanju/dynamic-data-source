package com.dynamic.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Copyright
 *
 * @author conanju
 * @since 2019/9/26 11:10
 */
public class MessagePointCuts {


    @Pointcut("execution(* com.dynamic.aopservice.**.*(..))")
    public void message() {

    }

}
