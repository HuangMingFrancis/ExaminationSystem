package com.example.francis.examinationsystem.util.net;

import android.util.Log;

import com.example.francis.examinationsystem.global.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wzn on 2017/1/6.
 * 添加header拦截器
 */

public class TestInterceptor implements Interceptor {
    private String contentType;

    public TestInterceptor() {
        contentType="application/x-jpg";
    }

    public TestInterceptor(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original=chain.request();
        Log.i("http","contentType: "+contentType);
        Request request=original.newBuilder()
                .addHeader("X-Bmob-Application-Id", Constants.Project.app_id)
                .addHeader("X-Bmob-REST-API-Key", Constants.Project.rest_api_key)
                .addHeader("Content-Type",contentType)
                .build();
        return chain.proceed(request);
    }
}
