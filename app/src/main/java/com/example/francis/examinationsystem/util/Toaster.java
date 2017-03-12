package com.example.francis.examinationsystem.util;

import android.content.Context;
import android.widget.Toast;

import com.example.francis.examinationsystem.global.App;

/**
 * Author: Felix Shi
 * Date:   2016-07-29 13:45
 * Description:
 */
public class Toaster {

    private static Toast toast;

    public static void showShort(CharSequence msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void showLong(CharSequence msg) {
        show(msg, Toast.LENGTH_LONG);
    }

    public static void show(CharSequence msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(App.mContext, msg, duration);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void closeToast() {
        if (toast != null)
            toast = null;
    }
}
