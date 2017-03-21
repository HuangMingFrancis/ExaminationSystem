package com.example.francis.examinationsystem.util.net;

import com.example.francis.examinationsystem.global.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wzn on 2017/3/21.
 */

class OkHttpClientHelper {
    private static OkHttpClient client;

    public  static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new HeaderInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor())
                    .connectTimeout(Constants.Project.networkTimeout, TimeUnit.SECONDS)
                    .readTimeout(Constants.Project.networkTimeout, TimeUnit.SECONDS)
                    .writeTimeout(Constants.Project.networkTimeout, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }


}
