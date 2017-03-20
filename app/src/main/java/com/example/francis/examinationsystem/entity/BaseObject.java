package com.example.francis.examinationsystem.entity;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

/**
 * 网络请求构造
 * Created by wzn on 2017/3/20.
 */

public class BaseObject {

    private String className;
    private String objectId;
    private String __type;
    private String _op;
    private String createdAt;
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setOp(String _op) {
        this._op = _op;
    }

    public JSONObject getCurrentData() {
        JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(this));
        if (_op != null) {
            Iterator iterator=  data.values().iterator();
            while (iterator.hasNext()){
                Log.i("===========",iterator.next().getClass().toString());
            }
        } else {

        }
        return data;
    }

    public String getCurrentDataStr() {
        return JSONObject.toJSONString(this);
    }

}
