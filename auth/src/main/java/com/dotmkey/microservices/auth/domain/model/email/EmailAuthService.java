package com.dotmkey.microservices.auth.domain.model.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailAuthService {
    private final EmailAccountActivationRepository emailAccountActivationRepository;

    public EmailAccountActivation attemptRegister(String email) {
        var emailAccountActivation = new EmailAccountActivation(this.emailAccountActivationRepository.nextId(), email);
        this.emailAccountActivationRepository.save(emailAccountActivation);

        return emailAccountActivation;
    }
}
