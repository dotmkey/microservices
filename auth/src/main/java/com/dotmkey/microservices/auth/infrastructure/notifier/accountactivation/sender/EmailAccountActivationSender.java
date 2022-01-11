package com.dotmkey.microservices.auth.infrastructure.notifier.accountactivation.sender;

import com.dotmkey.microservices.auth.domain.model.Account;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;
import com.dotmkey.microservices.auth.infrastructure.notifier.accountactivation.AccountActivationSender;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailAccountActivationSender implements AccountActivationSender<EmailAccountActivation> {
    private final MailSender mailSender;

    @Override
    public Account.Type type() {
        return Account.Type.EMAIL;
    }

    @Override
    public void send(EmailAccountActivation accountActivation) {
        var message = new SimpleMailMessage();
        message.setTo(accountActivation.getEmail());
        message.setFrom("no-reply@bank.com");
        message.setText("hello");

        this.mailSender.send(message);
    }
}
