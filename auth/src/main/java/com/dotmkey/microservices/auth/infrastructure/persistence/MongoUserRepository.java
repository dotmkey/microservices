package com.dotmkey.microservices.auth.infrastructure.persistence;

import com.dotmkey.microservices.auth.domain.model.AccountId;
import com.dotmkey.microservices.auth.domain.model.User;
import com.dotmkey.microservices.auth.domain.model.UserId;
import com.dotmkey.microservices.auth.domain.model.UserRepository;
import com.dotmkey.microservices.auth.infrastructure.persistence.jpa.MongoJpaUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class MongoUserRepository implements UserRepository {
    private final MongoJpaUserRepository mongoJpaUserRepository;

    @Override
    public UserId nextId() {
        return new UserId(UUID.randomUUID().toString());
    }

    @Override
    public AccountId nextAccountId() {
        return new AccountId(UUID.randomUUID().toString());
    }

    @Override
    public void save(User user) {
        this.mongoJpaUserRepository.save(user);
    }

    @Override
    public Optional<User> ofId(UserId id) {
        return this.mongoJpaUserRepository.findById(id);
    }
}
