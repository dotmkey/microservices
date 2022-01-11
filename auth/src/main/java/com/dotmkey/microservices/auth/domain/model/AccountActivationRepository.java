package com.dotmkey.microservices.auth.domain.model;

import java.util.Optional;

public interface AccountActivationRepository<T extends AccountActivation> {
    AccountActivationId nextId();
    void save(T accountActivation);
    Optional<T> ofId(AccountActivationId id);
    void delete(T accountActivation);
}
