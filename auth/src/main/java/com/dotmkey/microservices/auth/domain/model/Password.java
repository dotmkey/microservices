package com.dotmkey.microservices.auth.domain.model;

public record Password(String hash, PasswordHashAlgorithm algorithm) {
    public enum PasswordHashAlgorithm {
        BCRYPT
    }
}
