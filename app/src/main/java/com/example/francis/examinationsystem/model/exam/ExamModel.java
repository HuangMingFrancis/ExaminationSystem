package com.example.francis.examinationsystem.model.exam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.SubjectType;
import com.example.francis.examinationsystem.entity.bmob.DataResult;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Observable<ExamPaper> addExamPaper(final ExamPaper examPaper, List<Subject> lstSubjects) {
        Map<String, Object> object = new HashMap<>();
        List<Map<String, Object>> lstMap = new ArrayList<>();
        for (Subject subject : lstSubjects) {
            Map<String, Object> map = new HashMap<>();
            map.put("method", "POST");
            map.put("path", "/1/classes/Subject");
            map.put("body", subject);
            lstMap.add(map);
        }
        object.put("requests", lstMap);


        return RetrofitHelper.getRetrofit(Constants.Project.batchBaseUrl).create(ExamService.class)
                .addSubjectList(object)
                .flatMap(new Func1<List<Map<String, Object>>, Observable<ExamPaper>>() {
                    @Override
                    public Observable<ExamPaper> call(List<Map<String, Object>> lstMap) {
                        for (int i = 0; i < lstMap.size(); i++) {
                            Map<String, Object> object = lstMap.get(i);
                            if (examPaper.getLstSubjectIds() == null) {
                                examPaper.setLstSubjectIds(new ArrayList<String>());
                            }
                            examPaper.getLstSubjectIds().add((String) ((Map<String,Object>) object.get("success")).get("objectId"));
                        }
                        return examService.addExamPaper(examPaper);
                    }
                })
                .subscribeOn(Schedulers.io());


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


    public Observable<SubjectType> querySubjectType(String where) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", where);
        return examService.querySubjectType(jsonObject.toJSONString())
                .flatMap(new Func1<SubjectType, Observable<SubjectType>>() {
                    @Override
                    public Observable<SubjectType> call(SubjectType subjectType) {
                        if (subjectType != null) {
                            return Observable.just(subjectType);
                        }
                        return null;
                    }
                });
    }
}
