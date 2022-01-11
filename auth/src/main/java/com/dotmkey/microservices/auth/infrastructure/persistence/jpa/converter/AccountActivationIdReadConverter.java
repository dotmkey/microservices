package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.converter;

import com.dotmkey.microservices.auth.domain.model.AccountActivationId;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class AccountActivationIdReadConverter implements Converter<String, AccountActivationId> {
    @Override
    public AccountActivationId convert(@NonNull String source) {
        return new AccountActivationId(source);
    }
}
