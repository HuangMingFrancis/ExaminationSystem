package com.example.francis.examinationsystem.model.exam;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Solution;
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

import static android.R.attr.data;

/**
 * Created by wzn on 2017/3/21.
 */

public interface ExamService {
    @POST("classes/ExamPaper")
    Observable<ExamPaper> addExamPaper(@Body ExamPaper examPaper);

    @PUT("classes/ExamPaper/{objectId}")
    Observable<JSONObject> updateExamPaper(@Path("objectId") String objectId, @Body Map<String, Object> lstSubjects);

    @DELETE("classes/ExamPaper/{objectId}")
    Observable<JSONObject> deleteExamPaper(@Path("objectId") String objectId);

    @GET("classes/ExamPaper")
    Observable<DataResult<ExamPaper>> queryExamPaperList(@Query("where") String where);

    /**
     * 批量添加题目
     *
     * @param lstSubjects
     * @return
     */
    @POST("batch")
    Observable<List<Map<String, Object>>> addSubjectList(@Body Map<String, Object> lstSubjects);

    @POST("classes/Subject")
    Observable<Subject> addSubject(@Body Subject subject);

    @GET("classes/Subject")
    Observable<DataResult<Subject>> querySubject(@Query("where") String where);

    @GET("classes/Subject")
    Observable<JSONObject> querySubjectList(@Query("where") String where);

    @DELETE("classes/Subject")
    Observable<JSONObject> deleteSubject(@Query("objectId") String objectId);

    //查找subjectType
    @GET("classes/SubjectType")
    Observable<DataResult<SubjectType>> querySubjectType(@Query("where") String where);

    @GET("classes/Solution")
    Observable<DataResult<Solution>> querySolution(@Query("where") String where);

    @POST("batch")
    Observable<List<Map<String, Object>>> addSolutionList(@Body Map<String, Object> lstSolutions);

}
