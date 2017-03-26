package com.example.francis.examinationsystem.entity;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * 题目类型
 * Created by wzn on 2017/3/19.
 */

public class SubjectType extends BaseObject implements Parcelable {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
    }

    public SubjectType() {
    }

    protected SubjectType(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Creator<SubjectType> CREATOR = new Creator<SubjectType>() {
        @Override
        public SubjectType createFromParcel(Parcel source) {
            return new SubjectType(source);
        }

        @Override
        public SubjectType[] newArray(int size) {
            return new SubjectType[size];
        }
    };
}
