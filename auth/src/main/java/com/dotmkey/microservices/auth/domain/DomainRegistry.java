package com.dotmkey.microservices.auth.domain;

import com.dotmkey.microservices.auth.domain.model.PasswordHasher;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivationPolicy;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivationRepository;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DomainRegistry implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static PasswordHasher passwordHasher() {
        return applicationContext.getBean(PasswordHasher.class);
    }

    public static EmailAccountActivationPolicy emailAccountActivationPolicy() {
        return applicationContext.getBean(EmailAccountActivationPolicy.class);
    }

    public static EmailAccountActivationRepository emailAccountActivationRepository() {
        return applicationContext.getBean(EmailAccountActivationRepository.class);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = applicationContext;
        }
    }
}
