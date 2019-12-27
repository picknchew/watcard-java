package com.francochen.watcard.authentication;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {
    private final AuthenticationHandler authHandler;

    public AuthenticationInterceptor(AuthenticationHandler authHandler) {
        this.authHandler = authHandler;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder().header("Cookie", authHandler.getAuthCookie()).build();

        return chain.proceed(request);
    }
}
