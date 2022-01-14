package com.dotmkey.microservices.auth.infrastructure.notifier.accountactivation;

import com.dotmkey.microservices.auth.application.AccountRegistrationNotifier;
import com.dotmkey.microservices.auth.domain.model.Account;
import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivation;
import com.dotmkey.microservices.auth.infrastructure.configuration.properties.RegistrationEmailNotificationProperties;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

public interface AccountRegistrationSender<T extends AccountActivation> {
    Account.Type type();
    void send(T accountActivation);

    default boolean supports(AccountActivation accountActivation) {
        return accountActivation.getAccountType().equals(this.type());
    }

    @Component
    @AllArgsConstructor
    class EmailAccountRegistrationSender implements AccountRegistrationSender<EmailAccountActivation> {
        private final JavaMailSender mailSender;
        private final RegistrationEmailNotificationProperties messageProperties;
        private final AccountRegistrationNotifier.EmailTemplateResolver emailTemplateResolver;

        @Override
        public Account.Type type() {
            return Account.Type.EMAIL;
        }

        @Override
        public void send(EmailAccountActivation accountActivation) {
            try {
                var message = this.mailSender.createMimeMessage();
                var helper = new MimeMessageHelper(message, true);
                helper.setFrom(messageProperties.from());
                helper.setTo(accountActivation.getEmail());
                helper.setSubject(messageProperties.subject());
                helper.setText(this.emailTemplateResolver.resolve(accountActivation), true);

                this.mailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Component
    @AllArgsConstructor
    class PhoneAccountRegistrationSender implements AccountRegistrationSender<PhoneAccountActivation> {
        private final AccountRegistrationNotifier.PhoneTemplateResolver phoneTemplateResolver;

        @Override
        public Account.Type type() {
            return Account.Type.PHONE;
        }

        @Override
        public void send(PhoneAccountActivation accountActivation) {
        }
    }
}
