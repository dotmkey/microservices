package com.dotmkey.microservices.auth.infrastructure.persistence.jpa;

import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivation;

public interface MongoJpaPhoneAccountActivationRepository
        extends MongoJpaAccountActivationRepository<PhoneAccountActivation> {
}
