package com.example.francis.examinationsystem.model.exam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Solution;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.SubjectType;
import com.example.francis.examinationsystem.entity.bmob.DataResult;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.util.net.DataCompose;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.R.attr.type;

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
            subject.setObjectId(null);
            subject.setUpdatedAt(null);
            subject.setCreatedAt(null);
            Map<String, Object> map = new HashMap<>();
            map.put("method", "POST");
            map.put("path", "/1/classes/Subject");
            subject.setChecked(null);
            map.put("body", subject);
            lstMap.add(map);
        }
        object.put("requests", lstMap);

        return examService.addSubjectList(object)
                .flatMap(new Func1<List<Map<String, Object>>, Observable<ExamPaper>>() {
                    @Override
                    public Observable<ExamPaper> call(List<Map<String, Object>> lstMap) {
                        for (int i = 0; i < lstMap.size(); i++) {
                            Map<String, Object> object = lstMap.get(i);
                            if (examPaper.getLstSubjectIds() == null) {
                                examPaper.setLstSubjectIds(new ArrayList<String>());
                            }
                            examPaper.getLstSubjectIds().add((String) ((Map<String, Object>) object.get("success")).get("objectId"));
                        }
                        return examService.addExamPaper(examPaper);
                    }
                })
                .subscribeOn(Schedulers.io());

    }

    public Observable<JSONObject> updateExamPaper(ExamPaper examPaper) {
        Map<String, Object> examMap = new HashMap<>();
        examMap.put("name", examPaper.getName());
        examMap.put("des", examPaper.getDes());
        return examService.updateExamPaper(examPaper.getObjectId(), examMap)
                .subscribeOn(Schedulers.io());
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

    public Observable<Map<String, Object>> querySubject(final String subjectId, final Long studentId, final Long examPaperId) {
        final Map<String, Object> map = new HashMap<>();
        return querySubjectByObjectId(subjectId)
                .flatMap(new Func1<Subject, Observable<SubjectType>>() {
                    @Override
                    public Observable<SubjectType> call(Subject subject) {
                        map.put("subject", subject);
                        return querySubjectTypeById(subject.getSubjectTypeId());
                    }
                })
                .flatMap(new Func1<SubjectType, Observable<Solution>>() {
                    @Override
                    public Observable<Solution> call(SubjectType type) {
                        map.put("type", type);
                        return querySolution(((Subject) map.get("subject")).getId(), studentId, examPaperId);
                    }
                })
                .flatMap(new Func1<Solution, Observable<Map<String, Object>>>() {
                    @Override
                    public Observable<Map<String, Object>> call(Solution solution) {
                        map.put("solution", solution);
                        return Observable.just(map);
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public Observable<Subject> querySubjectByObjectId(String subjectId) {
        Map<String, Object> map = new HashMap<>();
        map.put("objectId", subjectId);
        return examService.querySubject(JSONObject.toJSONString(map))
                .flatMap(new DataCompose<Subject>())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Subject>> querySubjectList(String where) {
        return examService.querySubjectList(where)
                .flatMap(new Func1<JSONObject, Observable<List<Subject>>>() {
                    @Override
                    public Observable<List<Subject>> call(JSONObject jsonObject) {

                        return Observable.just(JSONArray.parseArray(
                                jsonObject.getJSONArray("results").toString(), Subject.class));
                    }
                })
                .subscribeOn(Schedulers.io());
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
                .flatMap(new DataCompose<SubjectType>())
                .subscribeOn(Schedulers.io());
    }

    public Observable<SubjectType> querySubjectTypeById(Long typeId) {
        SubjectType type = new SubjectType();
        type.setId(typeId);
        return examService.querySubjectType(JSONObject.toJSONString(type))
                .flatMap(new DataCompose<SubjectType>())
                .subscribeOn(Schedulers.io());
    }


    public Observable<Solution> querySolution(Long subjectId, Long studentId, Long examPaperId) {
        Solution solution = new Solution();
        solution.setSubjectId(subjectId);
        solution.setStudentId(studentId);
        solution.setExamPaperId(examPaperId);
        return examService.querySolution(JSONObject.toJSONString(solution))
                .flatMap(new DataCompose<Solution>())
                .observeOn(Schedulers.io());
    }

    public Observable<Boolean> addSolutionList(final List<Solution> lstSolutions) {
        Map<String, Object> object = new HashMap<>();
        List<Map<String, Object>> lstMap = new ArrayList<>();
        for (Solution solution : lstSolutions) {
            Map<String, Object> map = new HashMap<>();
            map.put("method", "POST");
            map.put("path", "/1/classes/Solution");
            map.put("body", solution);
            lstMap.add(map);
        }
        object.put("requests", lstMap);
        return examService.addSolutionList(object)
                .flatMap(new Func1<List<Map<String, Object>>, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(List<Map<String, Object>> maps) {
                        if (maps.size() == lstSolutions.size()) {
                            return Observable.just(true);
                        } else {
                            return Observable.just(false);

                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
