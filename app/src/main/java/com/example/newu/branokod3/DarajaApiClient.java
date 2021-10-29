package com.example.newu.branokod3;

import com.example.newu.branokod3.Interceptor.AccessInterceptor;
import com.example.newu.branokod3.Interceptor.AuthInterceptor;
import com.example.newu.branokod3.Services.STKPushServices;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import java.util.concurrent.TimeUnit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newu.branokod3.Constants.BASE_URL;
import static com.example.newu.branokod3.Constants.CONNECT_TIMEOUT;
import static com.example.newu.branokod3.Constants.READ_TIMEOUT;
import static com.example.newu.branokod3.Constants.WRITE_TIMEOUT;

public class DarajaApiClient {
    private Retrofit retrofit;
    private boolean isDebug;
    private boolean isGetAccessToken;
    private String mAuthToken;
    private HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();


    public DarajaApiClient setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
        return this;
    }

    public DarajaApiClient setAuthToken(String authToken) {
        mAuthToken = authToken;
        return this;
    }

    public DarajaApiClient setGetAccessToken(boolean getAccessToken) {
        isGetAccessToken = getAccessToken;
        return this;
    }


    private OkHttpClient.Builder okHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);

        return okHttpClient;
    }


    private Retrofit getRestAdapter() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());

        if (isDebug) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient.Builder okhttpBuilder = okHttpClient();

        if (isGetAccessToken) {
            okhttpBuilder.addInterceptor(new AccessInterceptor());
        }

        if (mAuthToken != null && !mAuthToken.isEmpty()) {
            okhttpBuilder.addInterceptor(new AuthInterceptor(mAuthToken));
        }

        builder.client(okhttpBuilder.build());

        retrofit = builder.build();

        return retrofit;
    }

    public STKPushServices mpesaService() {
        return getRestAdapter().create(STKPushServices.class);
    }
}

