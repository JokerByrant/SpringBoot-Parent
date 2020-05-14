package com.sxh.dbcobfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 配置多数据源
 * @author sxh
 * @date 2020/5/14
 */
@Configuration
public class DruidConfig {

    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.druid.primary")
    public DataSource druidPrimary() {
        DruidDataSource druidDataSource = new DruidDataSource();
//        List<Filter> filterList = new ArrayList<>();
//        filterList.add(wallFilter());
//        druidDataSource.setProxyFilters(filterList);
        return druidDataSource;
    }

    @Bean(name = "testDataSource")
    @Qualifier("testDataSource")
    @ConfigurationProperties(prefix="spring.datasource.druid.test")
    public DataSource druidTest() {
        return new DruidDataSource();
    }

//    @Bean
//    public WallFilter wallFilter(){
//        WallFilter wallFilter = new WallFilter();
//        wallFilter.setConfig(wallConfig());
//        return wallFilter;
//    }
//    
//    @Bean
//    public WallConfig wallConfig() {
//        WallConfig config = new WallConfig();
//        config.setMultiStatementAllow(true);//允许一次执行多条语句
//        config.setNoneBaseStatementAllow(true);//允许一次执行多条语句
//        return config;
//    }
}
