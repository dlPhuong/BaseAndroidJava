package com.example.baseandroidjava.API;

import com.example.baseandroidjava.models.entity.Customer;
import com.example.baseandroidjava.models.entity.User;
import com.example.baseandroidjava.models.request.LoginRequest;
import com.example.baseandroidjava.models.request.ManagedUserVM;
import com.example.baseandroidjava.models.response.BaseResponse;
import com.example.baseandroidjava.models.response.CustomerRes;
import com.example.baseandroidjava.models.response.CustomerResponse;
import com.example.baseandroidjava.models.response.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APICustomer {

//    @POST("api/customers")
//    Call<BaseResponse> createCustomer(@Body Customer customer);

    @POST("api/customers")
    Call<CustomerRes<Customer>> createCustomer(@Body Customer customer);

    @PUT("api/customers")
    Call<BaseResponse> updateCustomer(@Body Customer customer);

    @DELETE("api/customers")
    Call<BaseResponse> deleteCustomer(@Query("id") int id);

    @GET("api/getall")
    Call<CustomerResponse> getCustomer(@Query("page") int page, @Query("size") int size, @Query("sort") String sort);


}
