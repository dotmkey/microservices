package com.dotmkey.microservices.bank.domain.model;

import com.dotmkey.microservices.commons.domain.model.DomainObjectId;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BankAccountId extends DomainObjectId {
    public BankAccountId(String id) {
        super(id);
    }
}
