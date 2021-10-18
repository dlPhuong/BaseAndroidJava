package com.example.baseandroidjava.API;

import com.example.baseandroidjava.models.entity.User;
import com.example.baseandroidjava.models.request.LoginRequest;
import com.example.baseandroidjava.models.request.ManagedUserVM;
import com.example.baseandroidjava.models.response.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APILogin {
    @POST("api/authenticate")
    Call<TokenResponse> authentication(@Body LoginRequest loginRequest);

    @GET("api/account")
    Call<User> account();

    @POST("api/register")
    Call<String> register(@Body ManagedUserVM managedUserVM);

}