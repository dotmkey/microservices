package com.dotmkey.microservices.bank.infrastructure.configuration;

import com.dotmkey.microservices.bank.infrastructure.configuration.properties.DataSourceProperties;
import com.dotmkey.microservices.bank.infrastructure.configuration.properties.HibernateProperties;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties({DataSourceProperties.class, HibernateProperties.class})
public class HibernateConfiguration {
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        var dataSource = new PGSimpleDataSource();
        dataSource.setUrl(dataSourceProperties.url());
        dataSource.setUser(dataSourceProperties.username());
        dataSource.setPassword(dataSourceProperties.password());

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, HibernateProperties hibernateProperties) {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.dotmkey.bank.com.dotmkey.microservices.auth.domain.model");
        sessionFactory.setHibernateProperties(hibernateProperties(hibernateProperties));
        sessionFactory.setMappingDirectoryLocations(new PathResource("src/main/resources/hibernate/orm/mapping"));

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalSessionFactoryBean localSessionFactoryBean) {
        return new HibernateTransactionManager(Objects.requireNonNull(localSessionFactoryBean.getObject()));
    }

    private Properties hibernateProperties(HibernateProperties hibernateProperties) {
        var properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateProperties.hbm2ddl().auto());
        properties.setProperty("hibernate.dialect", hibernateProperties.dialect());

        return properties;
    }
}
