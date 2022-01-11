package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.converter;

import com.dotmkey.microservices.auth.domain.model.AccountId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class AccountIdWriteConverter implements Converter<AccountId, String> {
    @Override
    public String convert(AccountId source) {
        return source.getId();
    }
}
