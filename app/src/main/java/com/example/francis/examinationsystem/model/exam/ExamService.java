package com.example.francis.examinationsystem.model.exam;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.SubjectType;
import com.example.francis.examinationsystem.entity.bmob.DataResult;

import java.util.List;
import java.util.Map;

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

public interface ExamService {
    @POST("ExamPaper")
    Observable<ExamPaper> addExamPaper(@Body ExamPaper examPaper);

    @PUT("ExamPaper/{objectId}")
    Observable<JSONObject> updateExamPaper(@Path("objectId") String objectId, @Body Map<String,Object> lstSubjects);

    @DELETE("ExamPaper/{objectId}")
    Observable<JSONObject> deleteExamPaper(@Path("objectId") String objectId);

    @GET("ExamPaper")
    Observable<DataResult<ExamPaper>> queryExamPaperList(@Query("where") String where);

    /**
     * 批量添加题目
     *
     * @param lstSubjects
     * @return
     */
    @POST("batch")
    Observable<List<Map<String,Object>>> addSubjectList(@Body Map<String,Object> lstSubjects);

    @POST("Subject")
    Observable<Subject> addSubject(@Body Subject subject);

    @GET("Subject")
    Observable<Subject> querySubject(@Query("where") String where);

    @GET("Subject")
    Observable<JSONObject> querySubjectList(@Query("where") String where);

    @DELETE("Subject")
    Observable<JSONObject> deleteSubject(@Query("objectId") String objectId);

    //查找subjectType
    @GET("SubjectType")
    Observable<SubjectType> querySubjectType(@Query("where") String where);

}
