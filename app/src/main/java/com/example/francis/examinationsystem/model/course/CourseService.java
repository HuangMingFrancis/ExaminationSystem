package com.example.francis.examinationsystem.model.course;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.bmob.DataResult;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wzn on 2017/3/21.
 */

public interface CourseService {
    @POST("Course")
    Observable<Course> addCourse(@Body Course course);


    @GET("Course")
    Observable<DataResult<Course>> queryCourseList(@Query("where") String where);


    @PUT("Course/{objectId}")
    Observable<Course> updateCourse(@Path("objectId") String objectId, @Body Course course);

    @DELETE("Course")
    Observable<JSONObject> deleteCourse(@Query("objectId") String objectId);

    @GET("Course")
    Observable<DataResult<Course>> queryCourseListByCourseName(@Query("bql")String bql,@Query("value")String values);
}
