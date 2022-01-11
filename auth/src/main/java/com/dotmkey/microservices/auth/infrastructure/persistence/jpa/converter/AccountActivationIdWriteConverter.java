package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.converter;

import com.dotmkey.microservices.auth.domain.model.AccountActivationId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class AccountActivationIdWriteConverter implements Converter<AccountActivationId, String> {
    @Override
    public String convert(AccountActivationId source) {
        return source.getId();
    }
}
