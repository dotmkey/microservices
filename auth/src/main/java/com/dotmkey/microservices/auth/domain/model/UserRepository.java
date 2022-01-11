package com.dotmkey.microservices.auth.domain.model;

import java.util.Optional;

public interface UserRepository {
    UserId nextId();
    AccountId nextAccountId();
    void save(User user);
    Optional<User> ofId(UserId id);
}
