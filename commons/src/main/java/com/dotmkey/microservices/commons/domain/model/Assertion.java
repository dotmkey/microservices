package com.dotmkey.microservices.commons.domain.model;

public class Assertion {
    public static void assertA(Statement statement) {
        if (!statement.check()) {
            throw new DomainLogicException(statement);
        }
    }
}
