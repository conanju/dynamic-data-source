package com.dynamic;

import com.dynamic.util.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.dynamic", exclude = DataSourceAutoConfiguration.class)
public class ApplicationStarter {
    public static void main(String[] args) {
        ApplicationContextUtil applicationContextUtil = ApplicationContextUtil.getApplicationContextUtil();
        ApplicationContext applicationContext = SpringApplication.run(ApplicationStarter.class, args);
        applicationContextUtil.setApplicationContext(applicationContext);
    }
}
