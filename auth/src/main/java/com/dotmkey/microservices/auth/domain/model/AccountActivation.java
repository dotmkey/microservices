package com.dotmkey.microservices.auth.domain.model;

import com.dotmkey.microservices.commons.domain.model.IdentifiedDomainObject;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AccountActivation extends IdentifiedDomainObject {
    protected AccountActivationId id;
    protected Account.Type accountType = this.getAccountType();
    protected String activationCode = generateActivationCode();
    protected LocalDateTime registeredAt;

    abstract public Account.Type getAccountType();

    abstract protected String generateActivationCode();

    public AccountActivation(AccountActivationId id) {
        this.setId(id);
        this.setRegisteredAt(LocalDateTime.now());
    }
}
