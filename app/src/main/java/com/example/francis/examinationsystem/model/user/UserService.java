package com.example.francis.examinationsystem.model.user;

import com.example.francis.examinationsystem.entity.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wzn on 2017/3/21.
 */

public interface UserService {
    @POST("User")
    Observable<User> register(@Body User user);

    @GET("User")
    Observable<User> login(@Query("where") String where);
}
