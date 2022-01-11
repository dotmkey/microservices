package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.converter;

import com.dotmkey.microservices.auth.domain.model.AccountId;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class AccountIdReadConverter implements Converter<String, AccountId> {
    @Override
    public AccountId convert(@NonNull String source) {
        return new AccountId(source);
    }
}
