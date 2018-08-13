package com.allen.wanandroid.app;

import android.content.Context;

import com.allen.common.app.CommonApplication;
import com.allen.wanandroid.BuildConfig;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class App extends CommonApplication {
    public static Context getContext() {
        return context;
    }

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initBugly();
        initLeakCanary();
    }

    /**
     * 初始化bugly异常检测sdk
     */
    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_APP_ID, true);
    }

    /**
     * 检测内存泄露
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
