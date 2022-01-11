package com.dotmkey.microservices.auth.domain.model.email;

public interface EmailAccountActivationPolicy {
    String redirectUrl();
    int expirationPeriod();
}
