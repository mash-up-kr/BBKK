package org.seoul.kk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "org.seoul.kk.repository")
@EnableTransactionManagement
public class HibernateJpaConfig {

    private static final String ENTITY_LOCATE = "org.seoul.kk.entity";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(ENTITY_LOCATE);

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);

        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPersistenceUnitName("bbkk");
        emf.setJpaProperties(jpaProperties);

        return emf;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Profile(value = "production")
    @Bean(name = "jpaProperties")
    public Properties jpaProdProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", Boolean.toString(false));
        properties.setProperty("spring.jpa.hibernate.naming_strategy", "org.hibernate.cfg.EJB3NamingStrategy");
        properties.setProperty("hibernate.connection.CharSet", "UTF-8");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("hibernate.connection.useUnicode", Boolean.toString(true));
        return properties;
    }

    @Profile(value = { "default", "dev" })
    @Bean(name = "jpaProperties")
    public Properties jpaDevProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.format_sql", Boolean.toString(true));
        properties.setProperty("spring.jpa.hibernate.naming_strategy", "org.hibernate.cfg.EJB3NamingStrategy");
        properties.setProperty("hibernate.connection.CharSet", "UTF-8");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("hibernate.connection.useUnicode", Boolean.toString(true));
        return properties;
    }

    @Profile(value = "test")
    @Bean(name = "jpaProperties")
    public Properties jpaTestProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.format_sql", Boolean.toString(true));
        properties.setProperty("spring.jpa.hibernate.naming_strategy", "org.hibernate.cfg.EJB3NamingStrategy");
        properties.setProperty("hibernate.connection.CharSet", "UTF-8");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("hibernate.connection.useUnicode", Boolean.toString(true));
        return properties;
    }

}
