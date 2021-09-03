package com.canteko.wootaxi.interfaces;

import com.canteko.wootaxi.requests.LoginRequest;
import com.canteko.wootaxi.requests.VerifyApiKeyRequest;
import com.canteko.wootaxi.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiWootaxiInterface {

    @Headers({"Accept: application/json"})
    @POST("login/")
    Call<LoginResponse> login(@Body LoginRequest body);

    @Headers({"Accept: application/json"})
    @POST("verifyApiKey/")
    Call<LoginResponse> verifyApiKey(@Body VerifyApiKeyRequest body);

}
