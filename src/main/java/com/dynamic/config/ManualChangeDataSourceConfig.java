package com.dynamic.config;

import com.dynamic.common.DatabaseType;
import com.dynamic.common.DynamicDataSource;
import com.dynamic.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 根据业务类型，动态切换数据源
 *
 * @author kiki
 * @ClassName: ChangeDataSourceAdviceConfig
 * @date 2019年8月9日
 * @version: V1.0
 */
@Component
public class ManualChangeDataSourceConfig {
    private static Logger logger = LoggerFactory
            .getLogger(ManualChangeDataSourceConfig.class);


    public void setDataSourceKey(String name) {
        if (Constants.MASTER_DATABASE.equals(name)) {
            logger.info("operation db is........." + Constants.MASTER_DATABASE);
            DynamicDataSource.setDatabaseType(DatabaseType.dataBaseMaster);
        } else if (Constants.SLAVE_DATABASE_1.equals(name)) {
            logger.info("operation db is........." + Constants.SLAVE_DATABASE_1);
            DynamicDataSource.setDatabaseType(DatabaseType.dataBaseSlave1);
        }else if (Constants.SLAVE_DATABASE_2.equals(name)) {
            logger.info("operation db is........." + Constants.SLAVE_DATABASE_2);
            DynamicDataSource.setDatabaseType(DatabaseType.dataBaseSlave2);
        }
    }
}
