package org.seoul.kk.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.seoul.kk.config.properties.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(destroyMethod = "postDeregister")
    public BasicDataSource dataSource() {
        log.info("datasource properties : {}", dataSourceProperties.toString());
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setInitialSize(dataSourceProperties.getInitialSize());
        dataSource.setMaxTotal(dataSourceProperties.getMaxTotal());
        dataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
        dataSource.setMinIdle(dataSourceProperties.getMinIdle());
        dataSource.setMaxWaitMillis(dataSourceProperties.getMaxWait());
        dataSource.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(dataSourceProperties.isTestOnReturn());
        dataSource.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
        dataSource.setValidationQuery(dataSourceProperties.getValidateQuery());
        dataSource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getEvictTimeBetweenEvictionRunsMillis());
        dataSource.setNumTestsPerEvictionRun(dataSourceProperties.getEvictNumTestsPerEvictionRun());
        dataSource.setMinEvictableIdleTimeMillis(dataSourceProperties.getEvictMinEvictableIdleTimeMillis());

        return dataSource;
    }

}
