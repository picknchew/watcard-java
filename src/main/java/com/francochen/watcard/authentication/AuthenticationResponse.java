package com.francochen.watcard.authentication;

import pl.droidsonroids.jspoon.annotation.Selector;

public class AuthenticationResponse {
    @Selector(value = ".validation-summary-errors")
    private String error;

    private AuthenticationResponse() {}

    public String getError() {
        return error;
    }
}
