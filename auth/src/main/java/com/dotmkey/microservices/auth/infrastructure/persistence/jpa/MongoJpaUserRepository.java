package com.dotmkey.microservices.auth.infrastructure.persistence.jpa;

import com.dotmkey.microservices.auth.domain.model.User;
import com.dotmkey.microservices.auth.domain.model.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoJpaUserRepository extends MongoRepository<User, UserId> {
}
