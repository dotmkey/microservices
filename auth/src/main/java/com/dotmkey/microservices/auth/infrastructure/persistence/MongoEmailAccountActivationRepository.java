package com.dotmkey.microservices.auth.infrastructure.persistence;

import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivationRepository;
import com.dotmkey.microservices.auth.infrastructure.persistence.jpa.MongoJpaEmailAccountActivationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoEmailAccountActivationRepository extends MongoAccountActivationRepository<EmailAccountActivation>
        implements EmailAccountActivationRepository {
    public MongoEmailAccountActivationRepository(MongoJpaEmailAccountActivationRepository jpaRepository) {
        super(jpaRepository);
    }

    private MongoJpaEmailAccountActivationRepository getJpaRepository() {
        return (MongoJpaEmailAccountActivationRepository) this.jpaRepository;
    }

    @Override
    public Optional<EmailAccountActivation> ofEmail(String email) {
        return this.getJpaRepository().findByEmail(email);
    }
}
