package com.dotmkey.microservices.auth.domain.model;

import com.dotmkey.microservices.commons.domain.model.DomainObjectId;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class AccountId extends DomainObjectId {
    public AccountId(String id) {
        super(id);
    }
}
