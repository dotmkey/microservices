package com.dotmkey.microservices.auth.infrastructure.persistence.jpa;

import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import com.dotmkey.microservices.auth.domain.model.AccountActivationId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;

@Primary
public interface MongoJpaAccountActivationRepository<T extends AccountActivation>
        extends MongoRepository<T, AccountActivationId> {
}
