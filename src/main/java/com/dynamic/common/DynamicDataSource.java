package com.dynamic.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {
    // 保存一个线程安全的Database容器
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }


    public static void clearDatabaseType() {
        contextHolder.remove();
    }


    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    /**
     * 相当重要需要重写此方法，获取对应的数据源
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return getDatabaseType();
    }
}