package com.dotmkey.microservices.auth.infrastructure.configuration;

import com.dotmkey.microservices.auth.domain.model.PasswordHasher;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivationPolicy;
import com.dotmkey.microservices.auth.infrastructure.configuration.properties.AppProperties;
import com.dotmkey.microservices.auth.infrastructure.security.PasswordHasherImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties({AppProperties.class})
public class ApplicationConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordHasher passwordHasher(
            PasswordEncoder passwordEncoder, AppProperties appProperties) {
        return new PasswordHasherImpl(passwordEncoder, appProperties.passwordPolicy().algorithm());
    }

    @Bean
    public EmailAccountActivationPolicy emailAccountActivationPolicy(AppProperties appProperties) {
        return new EmailAccountActivationPolicy() {
            @Override
            public String redirectUrl() {
                return appProperties.accountActivationPolicy().email().redirectUrl();
            }

            @Override
            public int expirationPeriod() {
                return appProperties.accountActivationPolicy().email().expirationPeriod();
            }
        };
    }
}
