package com.dotmkey.microservices.auth.infrastructure.persistence.jpa;

import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;

import java.util.Optional;

public interface MongoJpaEmailAccountActivationRepository
        extends MongoJpaAccountActivationRepository<EmailAccountActivation> {
    Optional<EmailAccountActivation> findByEmail(String email);
}
