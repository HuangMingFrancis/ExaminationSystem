package com.example.francis.examinationsystem.model.course;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.bmob.DataResult;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wzn on 2017/3/21.
 */

public interface CourseService {
    @POST("Course")
    Observable<Course> addCourse(@Body Course course);

    @GET("Course")
    Observable<Course> queryCourse(@Query("where") String where );

    @GET("Course")
    Observable<DataResult<Course>> queryCourseList(@Query("where") String where);

    /**
     * 学生加入课程
     * @param objectId
     * @param course
     * @return
     */
    @PUT("Course")
    Observable<Course> joinCourse(@Query("objectId")String objectId,@Body Course course);

    @PUT("Course")
    Observable<Course> updateCourse(@Query("objectId")String objectId,@Body Course course);

    @DELETE("Course")
    Observable<JSONObject> deleteCourse(@Query("objectId")String objectId);


}
