package com.francochen.watcard.service;

import com.francochen.watcard.authentication.AuthenticationRequest;
import com.francochen.watcard.authentication.AuthenticationResponse;
import com.francochen.watcard.model.RequestVerificationToken;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.*;

public interface AuthenticationService {

    @FormUrlEncoded
    @POST("Account/LogOn")
    Single<Response<AuthenticationResponse>> authenticate(@FieldMap AuthenticationRequest authRequest,
                                                          @Header("Cookie") String cookies);

    @GET("Account/LogOn")
    Single<Response<RequestVerificationToken>> getRequestVerificationToken();
}
