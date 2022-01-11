package com.dotmkey.microservices.auth.domain.model.email;

import com.dotmkey.microservices.commons.domain.model.Statement;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

@AllArgsConstructor
public class EmailHasValidFormat extends EmailValidator implements Statement {
    private final String email;

    @Override
    public boolean check() {
        return this.isValid(email, null);
    }

    @Override
    public String message() {
        return "Email has invalid format";
    }
}
