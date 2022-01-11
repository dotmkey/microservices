package com.dotmkey.microservices.auth.infrastructure.notifier.accountactivation;

import com.dotmkey.microservices.auth.domain.model.Account;
import com.dotmkey.microservices.auth.domain.model.AccountActivation;

public interface AccountActivationSender<T extends AccountActivation> {
    Account.Type type();
    void send(T accountActivation);

    default boolean supports(AccountActivation accountActivation) {
        return accountActivation.getAccountType().equals(this.type());
    }
}
