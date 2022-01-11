package com.dotmkey.microservices.auth.application;

import com.dotmkey.microservices.auth.domain.model.AccountActivation;

public interface AccountActivationNotifier<T extends AccountActivation> {
    void notify(T accountActivation);
}
