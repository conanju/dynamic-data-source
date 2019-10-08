package com.dynamic.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Copyright
 *
 * @author conanju
 * @since 2019/9/24 17:20
 */
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContextUtil getApplicationContextUtil() {
        return new ApplicationContextUtil();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(Class var1) throws BeansException {
        return applicationContext.getBean(var1);
    }

    private ApplicationContextUtil() {
    }
}
