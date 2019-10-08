package com.dynamic.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dynamic.common.DatabaseType;
import com.dynamic.common.DynamicDataSource;
import com.dynamic.common.constant.Constants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = Constants.MASTER_DATABASE)
    public DataSource masterDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName",
                env.getProperty("spring.datasource.master.driverClassName"));
        props.put("url", env.getProperty("spring.datasource.master.url"));
        props.put("username",
                env.getProperty("spring.datasource.master.username"));
        props.put("password",
                env.getProperty("spring.datasource.master.password"));
        props.put("maxWait",
                env.getProperty("spring.datasource.master.maxWait"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean(name = Constants.SLAVE_DATABASE_1)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        DataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        return dataSource;

    }


    @Bean(name = Constants.SLAVE_DATABASE_2)
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slave2DataSource() {
        DataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        return dataSource;

    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier(Constants.MASTER_DATABASE) DataSource dataBaseMaster,
                                        @Qualifier(Constants.SLAVE_DATABASE_1) DataSource dataBaseSlave1,
                                        @Qualifier(Constants.SLAVE_DATABASE_2) DataSource dataBaseSlave2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.dataBaseMaster, dataBaseMaster);
        targetDataSources.put(DatabaseType.dataBaseSlave1, dataBaseSlave1);
        targetDataSources.put(DatabaseType.dataBaseSlave2, dataBaseSlave2);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        // 默认的datasource设置为kiki
        dataSource.setDefaultTargetDataSource(dataBaseMaster);
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */

    @Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory dynamicSqlSessionFactory(
            @Qualifier("dynamicDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/mapper/*.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean(name = "dynamicTransactionManager")
    public PlatformTransactionManager dynamicTransactionManager(DynamicDataSource dataSource) {
        System.out.println("dynamic datasource transaction manager is init");
        return new DataSourceTransactionManager(dataSource);
    }
}
