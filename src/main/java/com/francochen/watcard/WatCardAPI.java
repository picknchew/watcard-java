package com.francochen.watcard;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Retrofit;

import java.net.CookieManager;

public final class WatCardAPI {
    private static final String BASE_URL = "https://watcard.uwaterloo.ca/OneWeb/";

    private final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(JspoonConverterFactory.create())
            .client(new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(new CookieManager()))
                    .build())
            .baseUrl(BASE_URL)
            .build();

    public WatCardService createService() {
        return retrofit.create(WatCardService.class);
    }
}
