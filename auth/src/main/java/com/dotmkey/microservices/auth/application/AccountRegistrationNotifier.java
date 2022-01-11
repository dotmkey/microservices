package com.dotmkey.microservices.auth.application;

import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public interface AccountRegistrationNotifier<T extends AccountActivation> {
    void notify(T accountActivation);

    interface TemplateResolver<T extends AccountActivation> {
        String resolve(T accountActivation);
    }

    @Component
    class EmailTemplateResolver implements TemplateResolver<EmailAccountActivation> {
        @Value("classpath:mail/templates/registration.html")
        private Resource resource;

        @Override
        public String resolve(EmailAccountActivation accountActivation) {
            try {
                return String.format(
                    FileCopyUtils.copyToString(
                        new InputStreamReader(this.resource.getInputStream(), StandardCharsets.UTF_8)),
                    "https://auth.microservices.dotmkey.com",
                    accountActivation.getRedirectUrl(),
                    accountActivation.getActivationCode(),
                    accountActivation.getEmail()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Component
    class PhoneTemplateResolver implements TemplateResolver<PhoneAccountActivation> {
        @Override
        public String resolve(PhoneAccountActivation accountActivation) {
            return "";
        }
    }
}
