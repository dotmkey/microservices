package com.dotmkey.microservices.auth.domain.model;

public record Contact(Type type, String value) {
    public enum Type {
        EMAIL, PHONE, TELEGRAM, FACEBOOK, INSTAGRAM
    }

    public static Contact email(String value) {
        return new Contact(Type.EMAIL, value);
    }

    public static Contact phone(String value) {
        return new Contact(Type.PHONE, value);
    }
}
