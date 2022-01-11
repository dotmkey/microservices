package com.dotmkey.microservices.auth.infrastructure.persistence;

import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivation;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivationRepository;
import com.dotmkey.microservices.auth.infrastructure.persistence.jpa.MongoJpaPhoneAccountActivationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MongoPhoneAccountActivationRepository extends MongoAccountActivationRepository<PhoneAccountActivation>
        implements PhoneAccountActivationRepository {
    public MongoPhoneAccountActivationRepository(MongoJpaPhoneAccountActivationRepository jpaRepository) {
        super(jpaRepository);
    }
}
