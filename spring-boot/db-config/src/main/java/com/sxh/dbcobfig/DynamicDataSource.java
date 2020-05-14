package com.sxh.dbcobfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（需要继承AbstractRoutingDataSource ）
 * @author sxh
 * @date 2020/5/14
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 列出所有的数据源
     */
    public enum DatabaseType {
        // 主库
        primaryDataSource,
        // 从库
        testDataSource;
    }

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();
    
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataBaseType();
    }

    // 设置数据源
    public static void setDataBaseType(DatabaseType type){
        contextHolder.set(type);
    }
    
    // 获取当前数据源
    public static DatabaseType getDataBaseType() {
        return contextHolder.get();
    }
}
