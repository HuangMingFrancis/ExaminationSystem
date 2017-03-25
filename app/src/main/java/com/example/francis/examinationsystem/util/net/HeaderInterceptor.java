package com.example.francis.examinationsystem.util.net;

import android.text.TextUtils;
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

public class HeaderInterceptor implements Interceptor {
    private String contentType;



    public HeaderInterceptor(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request;
        if (!TextUtils.isEmpty(contentType)) {
            request = original.newBuilder()
                    .addHeader("X-Bmob-Application-Id", Constants.Project.app_id)
                    .addHeader("X-Bmob-REST-API-Key", Constants.Project.rest_api_key)
                    .addHeader("Content-Type", contentType)
                    .build();
        } else {
            request = original.newBuilder()
                    .addHeader("X-Bmob-Application-Id", Constants.Project.app_id)
                    .addHeader("X-Bmob-REST-API-Key", Constants.Project.rest_api_key)
                    .build();
        }

        return chain.proceed(request);
    }
}
