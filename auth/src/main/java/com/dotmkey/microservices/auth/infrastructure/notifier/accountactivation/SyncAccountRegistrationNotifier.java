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
import java.util.List;

@Component
@AllArgsConstructor
public class SyncAccountRegistrationNotifier<T extends AccountActivation> implements AccountRegistrationNotifier<T> {
    private final List<AccountActivationSender<T>> senders;

    @Override
    public void notify(T accountActivation) {
        this.senders.forEach(sender -> {
            if (sender.supports(accountActivation)) {
                sender.send(accountActivation);
            }
        });
    }

    public interface AccountActivationSender<T extends AccountActivation> {
        Account.Type type();
        void send(T accountActivation);

        default boolean supports(AccountActivation accountActivation) {
            return accountActivation.getAccountType().equals(this.type());
        }
    }

    @Component
    @AllArgsConstructor
    private static class EmailAccountActivationSender implements AccountActivationSender<EmailAccountActivation> {
        private final JavaMailSender mailSender;
        private final RegistrationEmailNotificationProperties messageProperties;
        private final EmailTemplateResolver emailTemplateResolver;

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
    private static class PhoneAccountActivationSender implements AccountActivationSender<PhoneAccountActivation> {
        private final PhoneTemplateResolver phoneTemplateResolver;

        @Override
        public Account.Type type() {
            return Account.Type.PHONE;
        }

        @Override
        public void send(PhoneAccountActivation accountActivation) {
        }
    }
}
