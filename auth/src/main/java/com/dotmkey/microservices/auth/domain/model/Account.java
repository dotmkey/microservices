package com.dotmkey.microservices.auth.domain.model;

import com.dotmkey.microservices.commons.domain.model.IdentifiedDomainObject;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Account extends IdentifiedDomainObject {
    protected AccountId id;
    protected Type type = this.getType();
    protected UserId userId;
    protected LocalDateTime registeredAt;
    protected LocalDateTime bannedAt;

    public enum Type {
        EMAIL, PHONE
    }

    abstract protected Type getType();

    public Account(AccountId id, User user) {
        this.setId(id);
        this.setUserId(user.getId());
        this.setRegisteredAt(LocalDateTime.now());
    }

    public interface Factory {
        Account createFor(User user);
    }
}
