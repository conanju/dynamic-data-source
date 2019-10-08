package com.dynamic.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright
 *
 * @author conanju
 * @since 2019/9/26 13:45
 */
@Slf4j
public class MyInvocationHandler implements InvocationHandler {

    private Object target;


    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("--------------before");
        Object result = method.invoke(target, args);
        log.info("after--------------");
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }
}
