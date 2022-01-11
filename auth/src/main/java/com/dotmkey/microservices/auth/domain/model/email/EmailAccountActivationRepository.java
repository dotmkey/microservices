package com.dotmkey.microservices.auth.domain.model.email;

import com.dotmkey.microservices.auth.domain.model.AccountActivationRepository;

import java.util.Optional;

public interface EmailAccountActivationRepository extends AccountActivationRepository<EmailAccountActivation> {
    Optional<EmailAccountActivation> ofEmail(String email);
}
