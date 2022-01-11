package com.dotmkey.microservices.auth.infrastructure.security;

import com.dotmkey.microservices.auth.domain.model.Password;
import com.dotmkey.microservices.auth.domain.model.PasswordHasher;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class PasswordHasherImpl implements PasswordHasher {
    private final PasswordEncoder passwordEncoder;
    private final String algorithm;

    @Override
    public Password hash(String rawPassword) {
        return new Password(
            this.passwordEncoder.encode(rawPassword),
            Password.PasswordHashAlgorithm.valueOf(this.algorithm)
        );
    }
}
