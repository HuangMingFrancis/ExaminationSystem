package com.example.francis.examinationsystem.model.exam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.bmob.DataResult;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;


import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wzn on 2017/3/21.
 */

public class ExamModel {
    ExamService examService;

    public ExamModel() {
        examService = RetrofitHelper.getRetrofit().create(ExamService.class);
    }


    public Observable<ExamPaper> addExamPaper(ExamPaper examPaper) {
        return examService.addExamPaper(examPaper);
    }

    public Observable<ExamPaper> updateExamPaper(ExamPaper examPaper) {
        return examService.updateExamPaper(examPaper.getObjectId(), examPaper);
    }

    public Observable<Boolean> deleteExamPaper(String objectId) {
        return examService.deleteExamPaper(objectId)
                .flatMap(new Func1<JSONObject, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(final JSONObject jsonObject) {
                        if (jsonObject.get("msg").equals("ok")) {
                            return Observable.just(true);
                        } else {
                            return Observable.just(false);
                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<ExamPaper>> queryExamPaperList(Long courseId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        return examService.queryExamPaperList(jsonObject.toJSONString())
                .flatMap(new Func1<DataResult<ExamPaper>, Observable<List<ExamPaper>>>() {
                    @Override
                    public Observable<List<ExamPaper>> call(DataResult<ExamPaper> dataResult) {
                        return Observable.just(dataResult.results);
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Long>> addSubjectList(List<Subject> lstSubjects) {
        return null;
    }

    public Observable<Subject> querySubject(String where) {
        return null;
    }

    public Observable<Subject> querySubjectList(String where) {
        return null;
    }

    public Observable<Boolean> deleteSubject(String objectId) {
        return examService.deleteSubject(objectId)
                .flatMap(new Func1<JSONObject, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(final JSONObject jsonObject) {
                        if (jsonObject.get("msg").equals("ok")) {
                            return Observable.just(true);
                        } else {
                            return Observable.just(false);
                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
