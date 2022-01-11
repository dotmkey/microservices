package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.converter;

import com.dotmkey.microservices.auth.domain.model.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class UserIdWriteConverter implements Converter<UserId, String> {
    @Override
    public String convert(UserId source) {
        return source.getId();
    }
}
