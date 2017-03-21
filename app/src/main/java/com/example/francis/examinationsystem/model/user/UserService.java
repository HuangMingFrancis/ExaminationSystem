package com.example.francis.examinationsystem.model.user;

import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.bmob.DataResult;


import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wzn on 2017/3/21.
 */

public interface UserService {
    @POST("User")
    Observable<User> register(@Body User user);

    @GET("User")
    Observable<DataResult<User>> login(@Query("where") String where);

    @PUT("User/{objectId}")
    Observable<User> updatePsw(@Path("objectId") String objectId, @Body User user);
}
