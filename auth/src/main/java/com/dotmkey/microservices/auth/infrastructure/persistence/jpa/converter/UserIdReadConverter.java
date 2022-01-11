package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.converter;

import com.dotmkey.microservices.auth.domain.model.UserId;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class UserIdReadConverter implements Converter<String, UserId> {
    @Override
    public UserId convert(@NonNull String source) {
        return new UserId(source);
    }
}
