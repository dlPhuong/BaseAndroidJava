package com.example.baseandroidjava.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseandroidjava.API.APILogin;
import com.example.baseandroidjava.Utils.Commons;
import com.example.baseandroidjava.Utils.ServiceGenerator;
import com.example.baseandroidjava.models.entity.User;
import com.example.baseandroidjava.models.request.LoginRequest;
import com.example.baseandroidjava.models.request.ManagedUserVM;
import com.example.baseandroidjava.models.response.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginViewModel extends ViewModel {
    private APILogin apiLogin = ServiceGenerator.createService(APILogin.class);
    private APILogin apiLoginWithAuth = ServiceGenerator.createService(APILogin.class, Commons.auth);

    public MutableLiveData<TokenResponse> postLogin(LoginRequest loginRequest){
        final MutableLiveData<TokenResponse> newsData = new MutableLiveData<>();
        apiLogin.authentication(loginRequest).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()){
                    newsData.setValue(response.body());
                }else{
                    TokenResponse res = new TokenResponse();
                    res.setIdToken(""+response.code());
                    newsData.setValue(res);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t);
            }
        });
        return newsData;
    }
    public MutableLiveData<User> getUserData(){
        final MutableLiveData<User> newsData = new MutableLiveData<>();
       apiLoginWithAuth.account().enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               if(response.isSuccessful()){
                   newsData.setValue(response.body());
               }
           }

           @Override
           public void onFailure(Call<User> call, Throwable t) {

           }
       });
        return newsData;
    }

    public MutableLiveData<String> signIn(ManagedUserVM managedUserVM){
        final MutableLiveData<String> newsData = new MutableLiveData<>();
        apiLogin.register(managedUserVM).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                newsData.setValue("đăng ký thành công");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                newsData.setValue("đăng ký thất bại");
            }
        });
        return newsData;
    }
}