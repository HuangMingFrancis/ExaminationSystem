package com.example.francis.examinationsystem.base;

/**
 * 基础链接区、定义一些公共的界面操作
 * Created by Quinn on 2017/3/3.
 */

public interface IBaseView {
    void showToast(String message);
    void showLoading();
    void hideLoading();
}
