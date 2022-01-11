package com.dotmkey.microservices.auth.infrastructure.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.notification.registration.email")
public record RegistrationEmailNotificationProperties(String subject, String from) {
}
