package com.example.francis.examinationsystem.util;

import com.example.francis.examinationsystem.global.App;

/**
 * dp . sp 转换为px的工具
 * Created by Francis on 2017-3-13.
 */

public class DisplayUtil {

    /**
     * 将px值转换为dip或dp值,保证尺寸大小不变
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue){
        final float scale= App.mContext.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

    /**
     * 将dip或dp值转换为px值,保证尺寸大小不变
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue){
        final float scale=App.mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }

    /**
     * 将px值转换为sp值,保证文字大小不变
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue){
        final float fontScale=App.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }

    /**
     * 将px值转换为sp值,保证文字大小不变
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue){
        final float fontScale=App.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }
}
