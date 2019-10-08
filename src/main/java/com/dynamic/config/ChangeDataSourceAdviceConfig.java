package com.dynamic.config;

import com.dynamic.common.DatabaseType;
import com.dynamic.common.DynamicDataSource;
import com.dynamic.common.constant.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 根据业务类型，动态切换数据源
 *
 * @author kiki
 * @ClassName: ChangeDataSourceAdviceConfig
 * @date 2019年8月9日
 * @version: V1.0
 */
@Aspect
@Component
//@Order注解用于定义的AOP先于事务执行(这里保证了在事务之前完成数据源的切换，以免切换数据源失效)
//@Order(-1)
public class ChangeDataSourceAdviceConfig {
    private static Logger logger = LoggerFactory
            .getLogger(ChangeDataSourceAdviceConfig.class);

    private static Pattern MASTER_PATTERN = Pattern.compile("^master");
    private static Pattern SLAVE_PATTERN_1 = Pattern.compile("^slave1");
    private static Pattern SLAVE_PATTERN_2 = Pattern.compile("^slave2");

    /**
     * 使用空方法定义切点表达式
     */
    @Pointcut("execution(* com.dynamic.service.**.*(..))")
    public void txConfig() {
    }

    @Before("txConfig()")
    public void setDataSourceKey(JoinPoint point) {
        // service的方法名
        String methodName = point.getSignature().getName();
        logger.info("切面请求请求的方法是：" + methodName);
        // 分别以insert|add|update|del|delete|edit开头的方法操作kiki这个datasource

        if (MASTER_PATTERN.matcher(methodName).find()) {
            logger.info("operation db is........." + Constants.MASTER_DATABASE);
            DynamicDataSource.setDatabaseType(DatabaseType.dataBaseMaster);
        } else if (SLAVE_PATTERN_1.matcher(methodName).find()) {
            logger.info("operation db is........." + Constants.SLAVE_DATABASE_1);
            DynamicDataSource.setDatabaseType(DatabaseType.dataBaseSlave1);
        } else if (SLAVE_PATTERN_2.matcher(methodName).find()) {
            logger.info("operation db is........." + Constants.SLAVE_DATABASE_2);
            DynamicDataSource.setDatabaseType(DatabaseType.dataBaseSlave2);
        } else {
            throw new RuntimeException("no database config error");
        }
    }

    @After("txConfig()")
    public void clearDataSource(JoinPoint joinPoint) {
        logger.info("clearDatabaseType");
        DynamicDataSource.clearDatabaseType();
    }
}
