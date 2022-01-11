package com.dotmkey.microservices.auth.infrastructure.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public record AppProperties(PasswordPolicy passwordPolicy, AccountActivationPolicy accountActivationPolicy) {
    public static record PasswordPolicy(String algorithm) {
    }

    public static record AccountActivationPolicy(Email email) {
        public static record Email(String redirectUrl, int expirationPeriod) {
        }
    }
}
