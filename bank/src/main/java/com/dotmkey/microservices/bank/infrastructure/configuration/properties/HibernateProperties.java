package com.dotmkey.microservices.bank.infrastructure.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("hibernate")
public record HibernateProperties(HibernateHbm2ddlProperties hbm2ddl, String dialect) {
    public static record HibernateHbm2ddlProperties(String auto) {
    }
}
