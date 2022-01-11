package com.dotmkey.microservices.bank.infrastructure.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.datasource")
public record DataSourceProperties(String url, String username, String password) {
}
