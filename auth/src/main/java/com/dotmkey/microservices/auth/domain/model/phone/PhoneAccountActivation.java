package com.dotmkey.microservices.auth.domain.model.phone;

import com.dotmkey.microservices.auth.domain.model.Account;
import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import com.dotmkey.microservices.auth.domain.model.AccountActivationId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_={@PersistenceConstructor}, access = AccessLevel.PRIVATE)
@Document(collection = "accountActivation")
public class PhoneAccountActivation extends AccountActivation {
    private String phone;

    public PhoneAccountActivation(AccountActivationId id, String phone) {
        super(id);
        this.setPhone(phone);
    }

    @Override
    public Account.Type getAccountType() {
        return Account.Type.PHONE;
    }

    @Override
    protected String generateActivationCode() {
        return RandomStringUtils.randomNumeric(4);
    }
}
