package com.example.baseandroidjava.Utils;

import android.text.TextUtils;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {



    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Commons.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, Commons.auth);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.readTimeout(10, TimeUnit.SECONDS) //ngắt kết nối sau 10s nếu sever không trả dữ liệu về
                        .writeTimeout(10, TimeUnit.SECONDS) //
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true) //cố gắng kết nối lại nếu bị trục trặc về mạng
                        .protocols(Collections.singletonList(Protocol.HTTP_1_1)) //tránh bị lỗi khi kết nối không được với sever
                        .addInterceptor(interceptor);


                builder.client(httpClient.build());
                retrofit = builder.build();
                return retrofit.create(serviceClass);
            }
        }

        return retrofit.create(serviceClass);
    }
}