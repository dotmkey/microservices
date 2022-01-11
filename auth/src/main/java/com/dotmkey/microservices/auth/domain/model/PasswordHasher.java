package com.dotmkey.microservices.auth.domain.model;

public interface PasswordHasher {
    Password hash(String rawPassword);
}
