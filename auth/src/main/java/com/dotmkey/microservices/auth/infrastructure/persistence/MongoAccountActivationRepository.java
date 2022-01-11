package com.dotmkey.microservices.auth.infrastructure.persistence;

import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import com.dotmkey.microservices.auth.domain.model.AccountActivationId;
import com.dotmkey.microservices.auth.domain.model.AccountActivationRepository;
import com.dotmkey.microservices.auth.infrastructure.persistence.jpa.MongoJpaAccountActivationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class MongoAccountActivationRepository<T extends AccountActivation> implements AccountActivationRepository<T> {
    protected final MongoJpaAccountActivationRepository<T> jpaRepository;

    @Override
    public AccountActivationId nextId() {
        return new AccountActivationId(UUID.randomUUID().toString());
    }

    @Override
    public void save(T accountActivation) {
        this.jpaRepository.save(accountActivation);
    }

    @Override
    public Optional<T> ofId(AccountActivationId id) {
        return this.jpaRepository.findById(id);
    }

    @Override
    public void delete(T accountActivation) {
        this.jpaRepository.delete(accountActivation);
    }
}
