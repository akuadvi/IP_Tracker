package com.sawolabs.sdkdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface getLocation {
    @GET("v1")
    Call<Example> getIp(@Query("apiKey") String key, @Query("ipAddress") String Address);

    @GET
    Call<currentIp> getCurr(@Url String url);
}
