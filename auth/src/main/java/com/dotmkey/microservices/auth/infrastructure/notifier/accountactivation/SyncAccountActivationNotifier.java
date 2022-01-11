package com.dotmkey.microservices.auth.infrastructure.notifier.accountactivation;

import com.dotmkey.microservices.auth.application.AccountActivationNotifier;
import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SyncAccountActivationNotifier<T extends AccountActivation> implements AccountActivationNotifier<T> {
    private final List<AccountActivationSender<T>> senders;

    @Override
    public void notify(T accountActivation) {
        this.senders.forEach(sender -> {
            if (sender.supports(accountActivation)) {
                sender.send(accountActivation);
            }
        });
    }
}
