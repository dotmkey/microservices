package com.dotmkey.microservices.auth.domain.model.email;

import com.dotmkey.microservices.auth.domain.DomainRegistry;
import com.dotmkey.microservices.auth.domain.model.Account;
import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import com.dotmkey.microservices.auth.domain.model.AccountActivationId;
import com.dotmkey.microservices.commons.domain.model.Assertion;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_={@PersistenceConstructor}, access = AccessLevel.PRIVATE)
@Document(collection = "accountActivation")
public class EmailAccountActivation extends AccountActivation {
    private String email;
    private String redirectUrl;

    public EmailAccountActivation(AccountActivationId id, @NonNull String email) {
        super(id);
        this.setEmail(email);
        this.setRedirectUrl(DomainRegistry.emailAccountActivationPolicy().redirectUrl());
    }

    @Override
    public Account.Type getAccountType() {
        return Account.Type.EMAIL;
    }

    @Override
    protected String generateActivationCode() {
        return UUID.randomUUID().toString();
    }

    private void setEmail(String email) {
        Assertion.assertA(new EmailHasValidFormat(email));
        Assertion.assertA(new AccountActivationOfSuchEmailDoesNotExist(email));

        this.email = email;
    }
}
