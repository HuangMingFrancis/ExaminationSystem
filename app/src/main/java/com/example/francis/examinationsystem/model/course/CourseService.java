package com.example.francis.examinationsystem.model.course;

import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.bmob.DataResult;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by wzn on 2017/3/21.
 */

public interface CourseService {
    @POST("classes/Course")
    Observable<Course> addCourse(@Body Course course);


    @GET("classes/Course")
    Observable<DataResult<Course>> queryCourseList(@Query("where") String where);


    @PUT("classes/Course/{objectId}")
    Observable<Course> updateCourse(@Path("objectId") String objectId, @Body Course course);

    @DELETE("classes/Course/{objectId}")
    Observable<com.alibaba.fastjson.JSONObject> deleteCourse(@Path("objectId") String objectId);

    @GET("cloudQuery")
    Observable<DataResult<Course>> queryCourseListByCourseName(@Query("bql") String bql, @Query("values") String values);
}
