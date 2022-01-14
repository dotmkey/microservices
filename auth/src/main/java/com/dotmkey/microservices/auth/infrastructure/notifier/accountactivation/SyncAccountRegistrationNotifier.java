package com.dotmkey.microservices.auth.infrastructure.notifier.accountactivation;

import com.dotmkey.microservices.auth.application.AccountRegistrationNotifier;
import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SyncAccountRegistrationNotifier implements AccountRegistrationNotifier<AccountActivation> {
    public final List<AccountRegistrationSender> senders;

    @Override
    public void notify(AccountActivation accountActivation) {
        this.senders.forEach(sender -> {
            if (sender.supports(accountActivation)) {
                sender.send(accountActivation);
            }
        });
    }
}
