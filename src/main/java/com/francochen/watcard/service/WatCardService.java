package com.francochen.watcard.service;

import com.francochen.watcard.model.PersonalInfo;
import com.francochen.watcard.model.balance.Balances;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public interface WatCardService {

    @GET("Account/Personal")
    Single<Response<PersonalInfo>> getPersonalInfo();

    @GET("Financial/Balances")
    Single<Response<Balances>> getBalances();
}
