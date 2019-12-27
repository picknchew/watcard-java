package com.francochen.watcard.authentication;

import com.francochen.watcard.service.AuthenticationService;
import io.reactivex.Single;
import retrofit2.Response;

import java.net.HttpCookie;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AuthenticationHandler {
    // in minutes
    private static final int DEFAULT_ACCOUNT_MODE = 0;
    private static final int SESSION_LIFETIME = 5;
    private static final int AUTH_SUCCESS_CODE = 302;

    private final String account, pin;

    private final AuthenticationService service;
    private final AtomicReference<String> authCookie = new AtomicReference<>();
    private final AtomicReference<Instant> lastAuthenticated = new AtomicReference<>();

    public AuthenticationHandler(String account, String pin, AuthenticationService service) {
        this.account = account;
        this.pin = pin;
        this.service = service;
    }

    public Single<Response<AuthenticationResponse>> renewSession() {
        return service.getRequestVerificationToken()
                .doOnSuccess(tokenResponse -> {
                    if (!tokenResponse.isSuccessful()) {
                        throw new AuthenticationException("Failed to retrieve verification token");
                    }
                })
                .flatMap(tokenResponse -> service.authenticate(account, pin, DEFAULT_ACCOUNT_MODE, tokenResponse.body(),
                        HttpCookie.parse(tokenResponse.headers().get("Set-Cookie"))
                                .stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(":"))))
                .doOnSuccess(authResponse -> {
                    if (authResponse.code() != AUTH_SUCCESS_CODE) {
                        throw new AuthenticationException(authResponse.body().getError());
                    }

                    lastAuthenticated.set(Instant.now());

                    authCookie.set(HttpCookie.parse(authResponse.headers().get("Set-Cookie")).stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(";")));
                });
    }

    public String getAuthCookie() {
        return authCookie.get();
    }

    public boolean hasSessionExpired() {
        return lastAuthenticated.get() == null ||
                Duration.between(lastAuthenticated.get(), Instant.now()).toMinutes() >= SESSION_LIFETIME;
    }
}
