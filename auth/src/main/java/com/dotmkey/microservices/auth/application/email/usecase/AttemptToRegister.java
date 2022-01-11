package com.dotmkey.microservices.auth.application.email.usecase;

import com.dotmkey.microservices.auth.application.AccountRegistrationNotifier;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;
import com.dotmkey.microservices.auth.domain.model.email.EmailAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttemptToRegister {
    private final EmailAuthService authService;
    private final AccountRegistrationNotifier<EmailAccountActivation> accountRegistrationNotifier;

    public void execute(String email) {
        var accountActivation = this.authService.attemptRegister(email);
        this.accountRegistrationNotifier.notify(accountActivation);
    }
}
