package com.francochen.watcard;

import com.francochen.watcard.model.PersonalInfo;
import com.francochen.watcard.model.RequestVerificationToken;
import com.francochen.watcard.model.balance.Balances;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WatCardService {

    @FormUrlEncoded
    @POST("Account/LogOn")
    Call<ResponseBody> authenticate(@Field("Account") String account, @Field("Password") String pin, @Field("AccountMode") int accountMode,
                                    @Field("__RequestVerificationToken") RequestVerificationToken token);

    @GET("Account/LogOn")
    Call<RequestVerificationToken> getRequestVerificationToken();

    @GET("Account/Personal")
    Call<PersonalInfo> getPersonalInfo();

    @GET("Financial/Balances")
    Call<Balances> getBalances();
}
