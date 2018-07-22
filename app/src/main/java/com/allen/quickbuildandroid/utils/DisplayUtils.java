package com.allen.quickbuildandroid.utils;

import android.util.DisplayMetrics;

import com.allen.quickbuildandroid.app.App;


/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/02/05
 *      desc    : 屏幕信息有关的类，包括屏幕的长宽、分辨率、长度换算
 *      version : 1.0
 * </pre>
 */
public class DisplayUtils {

    private static DisplayMetrics metrics;

    static {
        metrics = App.getContext().getResources().getDisplayMetrics();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return metrics;
    }

    /**
     * 获取屏幕的像素密度(DPI)
     *
     * @return
     */
    public static int getDpi() {
        return (int) (metrics.density * 160);
    }

    /**
     * 获取px与dp的比值，比如返回2，1dp=2px
     */
    public static float getDensity() {
        return metrics.density;
    }

    /**
     * 获取屏幕宽度，以像素为单位
     *
     * @return
     */
    public static int getScreenWidth() {
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕高度，以像素为单位
     *
     * @return
     */
    public static int getScreenHeight() {
        return metrics.heightPixels;
    }

    /**
     * 字体大小单位由sp转为px
     *
     * @param spValue 原sp单位
     * @return 新的px单位
     */
    public static int sp2px(float spValue) {
        final float fontScale = metrics.scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 字体大小由px转为sp
     *
     * @param pxValue 原px单位
     * @return 新的sp单位
     */
    public static int px2sp(float pxValue) {
        final float fontScale = metrics.scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 长度单位由dp转为px
     *
     * @param dpValue 原dp单位
     * @return 转换后的px单位
     */
    public static int dp2px(float dpValue) {
        final float density = getDensity();
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * 长度单位由px转为dp
     *
     * @param pxValue 原px单位
     * @return 转换后的dp单位
     */
    public static int px2dp(float pxValue) {
        final float density = getDensity();
        return (int) (pxValue / density + 0.5f);
    }

}
