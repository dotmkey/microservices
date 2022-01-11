package com.dotmkey.microservices.commons.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainLogicException extends RuntimeException {
    private final Statement statement;
}
