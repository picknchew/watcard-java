package com.francochen.watcard;

import com.francochen.watcard.authentication.AuthenticationHandler;
import com.francochen.watcard.authentication.AuthenticationInterceptor;
import com.francochen.watcard.model.PersonalInfo;
import com.francochen.watcard.model.balance.Balances;
import com.francochen.watcard.service.AuthenticationService;
import com.francochen.watcard.service.WatCardService;
import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.function.Supplier;

public final class WatCardClient {
    private static final String BASE_URL = "https://watcard.uwaterloo.ca/OneWeb/";

    private final AuthenticationHandler authHandler;
    private final WatCardService watCardService;

    private WatCardClient(Builder builder) {
        authHandler = new AuthenticationHandler(builder.account, builder.pin,
                new Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                        .addConverterFactory(JspoonConverterFactory.create())
                        .client(new OkHttpClient.Builder()
                                .followRedirects(false).build())
                        .baseUrl(BASE_URL)
                        .build().create(AuthenticationService.class));

        watCardService = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(JspoonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .followRedirects(false)
                        .addInterceptor(new AuthenticationInterceptor(authHandler)).build())
                .baseUrl(BASE_URL)
                .build().create(WatCardService.class);
    }

    public Completable hasValidCredentials() {
        return authHandler.renewSession()
                .flatMapCompletable(authResponse -> Completable.complete());
    }

    public Single<Balances> getBalances() {
        return getTransformedSingle(watCardService::getBalances);
    }

    public Single<PersonalInfo> getPersonalInfo() {
        return getTransformedSingle(watCardService::getPersonalInfo);
    }

    private <T> Single<T> getTransformedSingle(Supplier<Single<Response<T>>> supplier) {
        if (authHandler.hasSessionExpired()) {
            return authHandler.renewSession()
                    .flatMap(response -> supplier.get())
                    .map(response -> {
                        if (response.code() != 200) {
                            throw new IOException("Failed to retrieve data from server");
                        }

                        return response.body();
                    });
        }

        return supplier.get()
                .flatMap(response -> {
                    if (response.code() != 200) {
                        return authHandler.renewSession()
                                .flatMap(authResponse -> supplier.get())
                                .map(newResponse -> {
                                    if (newResponse.code() != 200) {
                                        throw new IOException("Failed to retrieve data from server");
                                    }

                                    return newResponse.body();
                                });
                    }

                    return Single.just(response.body());
                })
                .onErrorResumeNext(throwable -> authHandler.renewSession()
                        .flatMap(response -> supplier.get())
                        .map(Response::body));
    }

    public static class Builder {
        private String account;
        private String pin;

        public Builder account(String account) {
            this.account = account;
            return this;
        }

        public Builder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public WatCardClient build() {
            return new WatCardClient(this);
        }
    }
}
