package com.dotmkey.microservices.auth.port.http.controller.test;

import com.dotmkey.microservices.auth.domain.model.*;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccount;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivationRepository;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccount;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivation;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivationRepository;
import com.dotmkey.microservices.auth.infrastructure.persistence.MongoEmailAccountActivationRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    private final UserRepository userRepository;

    @GetMapping("test")
    public User test() {
        var user = new User(
            this.userRepository.nextId(),
            new EmailAccount.Factory(this.userRepository.nextAccountId(), "dotmkey@gmail.com", "123456"),
            "name",
            "surname",
            "lastname"
        );
        user.addAccount(new PhoneAccount.Factory(this.userRepository.nextAccountId(), "89313316627", "123456"));
        user.addContact(Contact.email("dotmkey@gmail.com"));
        user.addContact(Contact.phone("89313316627"));
        this.userRepository.save(user);
        return this.userRepository.ofId(user.getId()).orElseThrow();
    }

    private final AccountActivationRepository<AccountActivation> accountActivationRepository;
    private final EmailAccountActivationRepository emailAccountActivationRepository;
    private final PhoneAccountActivationRepository phoneAccountActivationRepository;
    private final MongoEmailAccountActivationRepository mongoEmailAccountActivationRepository;

    @GetMapping("test2")
    public List<AccountActivation> test2() {
        var emailAA = new EmailAccountActivation(this.emailAccountActivationRepository.nextId(), "dotmkey@gmail.com");
        this.emailAccountActivationRepository.save(emailAA);
        emailAA = this.emailAccountActivationRepository.ofId(emailAA.getId()).orElseThrow();

        var phoneAA = new PhoneAccountActivation(this.phoneAccountActivationRepository.nextId(), "89313316627");
        this.phoneAccountActivationRepository.save(phoneAA);
        phoneAA = this.phoneAccountActivationRepository.ofId(phoneAA.getId()).orElse(null);

        return List.of(emailAA, phoneAA);
    }
}
