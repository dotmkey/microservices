package com.dotmkey.microservices.bank.domain.model;

import com.dotmkey.microservices.commons.domain.model.DomainObjectId;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ClientId extends DomainObjectId {
    public ClientId(String id) {
        super(id);
    }
}
