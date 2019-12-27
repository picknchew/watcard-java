package com.francochen.watcard.model;

import pl.droidsonroids.jspoon.annotation.Selector;

public class RequestVerificationToken {
    private static final int VERIFY_TOKEN_INDEX = 5;

    @Selector(value = "input", index = VERIFY_TOKEN_INDEX, attr = "value")
    private String token;

    private RequestVerificationToken() {}

    public String toString() {
        return token;
    }
}
