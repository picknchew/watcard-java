package com.francochen.watcard.service;

import com.francochen.watcard.model.PersonalInfo;
import com.francochen.watcard.model.balance.Balances;
import com.francochen.watcard.model.transaction.TransactionRequest;
import com.francochen.watcard.model.transaction.Transactions;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WatCardService {

    @GET("Account/Personal")
    Single<Response<PersonalInfo>> getPersonalInfo();

    @GET("Financial/Balances")
    Single<Response<Balances>> getBalances();

    @GET("Financial/TransactionsPass")
    Single<Response<Transactions>> getTransactions(@QueryMap TransactionRequest request);
}