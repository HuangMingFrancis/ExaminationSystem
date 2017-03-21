package com.example.francis.examinationsystem.model.exam;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Subject;

import org.json.JSONArray;

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

public interface ExamService {
    @POST("ExamPaper")
    Observable<ExamPaper> addExamPaper(@Body ExamPaper examPaper);

    @PUT("ExamPaper")
    Observable<ExamPaper> updateExamPaper(@Query("objectId") String objectId, @Body ExamPaper examPaper);

    @DELETE("ExamPaper")
    Observable<JSONObject> deleteExamPaper(@Query("objectId") String objectId);

    @GET("ExamPaper")
    Observable<JSONObject> queryExamPaperList(@Query("where") String where);

    /**
     * 批量添加题目
     *
     * @param lstSubjects
     * @return
     */
    @POST("batch")
    Observable<JSONArray> addSubjectList(@Body JSONArray lstSubjects);

    @POST("Subject")
    Observable<Subject> addSubject(@Body Subject subject);

    @GET("Subject")
    Observable<Subject> querySubject(@Query("where") String where);

    @GET("Subject")
    Observable<JSONObject> querySubjectList(@Query("where") String where);

    @DELETE("Subject")
    Observable<JSONObject> deleteSubject(@Query("objectId") String objectId);


}
