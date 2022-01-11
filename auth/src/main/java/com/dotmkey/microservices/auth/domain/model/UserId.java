package com.dotmkey.microservices.auth.domain.model;

import com.dotmkey.microservices.commons.domain.model.DomainObjectId;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UserId extends DomainObjectId {
    public UserId(String id) {
        super(id);
    }
}
