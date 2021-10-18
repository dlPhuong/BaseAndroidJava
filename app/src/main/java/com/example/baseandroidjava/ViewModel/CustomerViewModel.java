package com.example.baseandroidjava.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseandroidjava.API.APICustomer;
import com.example.baseandroidjava.API.APILogin;
import com.example.baseandroidjava.Utils.Commons;
import com.example.baseandroidjava.Utils.ServiceGenerator;
import com.example.baseandroidjava.models.entity.Customer;
import com.example.baseandroidjava.models.entity.User;
import com.example.baseandroidjava.models.request.LoginRequest;
import com.example.baseandroidjava.models.request.ManagedUserVM;
import com.example.baseandroidjava.models.response.BaseResponse;
import com.example.baseandroidjava.models.response.CustomerResponse;
import com.example.baseandroidjava.models.response.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerViewModel extends ViewModel {
    private APICustomer apiAuth = ServiceGenerator.createService(APICustomer.class, Commons.auth);

    public MutableLiveData<CustomerResponse> loadCustomer(int page ,int size , String sort){
        final MutableLiveData<CustomerResponse> newsData = new MutableLiveData<>();
        apiAuth.getCustomer(page,size,sort).enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
        return newsData;
    }

    public MutableLiveData<BaseResponse> createCustomer(Customer customer){
        MutableLiveData<BaseResponse> newdata = new MutableLiveData<>();
        apiAuth.createCustomer(customer).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.isSuccessful()){
                    newdata.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
        return newdata;
    }


    public MutableLiveData<BaseResponse> updateCustomer(Customer customer){
        MutableLiveData<BaseResponse> newdata = new MutableLiveData<>();
        apiAuth.updateCustomer(customer).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.isSuccessful()){
                    newdata.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
        return newdata;
    }

    public MutableLiveData<BaseResponse> deleteCustomer(int id){
        MutableLiveData<BaseResponse> newdata = new MutableLiveData<>();
        apiAuth.deleteCustomer(id).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.isSuccessful()){
                    newdata.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
        return newdata;
    }

}