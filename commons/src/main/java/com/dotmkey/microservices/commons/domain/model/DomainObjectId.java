package com.dotmkey.microservices.commons.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public abstract class DomainObjectId {
    protected final String id;

    public DomainObjectId(@NonNull String id) {
        UUID.fromString(id);
        this.id = id;
    }
}
