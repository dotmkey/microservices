package com.dotmkey.microservices.auth.domain.model.email;

import com.dotmkey.microservices.auth.domain.DomainRegistry;
import com.dotmkey.microservices.commons.domain.model.Statement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountActivationOfSuchEmailDoesNotExist implements Statement {
    private final String email;

    @Override
    public boolean check() {
        return DomainRegistry.emailAccountActivationRepository().ofEmail(email).isEmpty();
    }

    @Override
    public String message() {
        return String.format("Account activation of email=%s already exist", this.email);
    }
}
