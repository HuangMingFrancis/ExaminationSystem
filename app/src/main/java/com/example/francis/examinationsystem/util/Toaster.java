package com.example.francis.examinationsystem.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Author: Felix Shi
 * Date:   2016-07-29 13:45
 * Description:
 */
public class Toaster {

    private static Toast toast;

    public static void showShort(Context context, CharSequence msg) {
        show(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showLong(Context context, CharSequence msg) {
        show(context, msg, Toast.LENGTH_LONG);
    }

    public static void show(Context context, CharSequence msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
    public static void closeToast(){
        if (toast!=null)
            toast=null;
    }
}
