package com.francochen.watcard.authentication;

import com.francochen.watcard.model.RequestVerificationToken;

import java.util.HashMap;

public class AuthenticationRequest extends HashMap<String, String> {

    public AuthenticationRequest(String account, String pin, int accountMode, RequestVerificationToken token) {
        put("Account", account);
        put("Password", pin);
        put("AccountMode", Integer.toString(accountMode));
        put("__RequestVerificationToken", token.toString());
    }
}
