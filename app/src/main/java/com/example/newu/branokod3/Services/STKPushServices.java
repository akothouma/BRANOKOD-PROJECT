package com.example.newu.branokod3.Services;

import com.example.newu.branokod3.Model.AccessToken;
import com.example.newu.branokod3.Model.STKPush;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class STKPushServices {
    public interface STKPushService {
        @POST("mpesa/stkpush/v1/processrequest")
        Call<STKPush> sendPush(@Body STKPush stkPush);

        @GET("oauth/v1/generate?grant_type=client_credentials")
        Call<AccessToken> getAccessToken();

    }

    public Call<AccessToken> getAccessToken() {
        return getAccessToken();
    }

    public Call<STKPush> sendPush(STKPush stkPush) {
        return sendPush(stkPush);
    }


}
