package com.allen.wanandroid.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.allen.common.arouter.ARouterHelper;
import com.allen.wanandroid.R;
import com.library.base.base.BaseActivity;
import com.library.base.permission.PermissionListener;
import com.library.base.utils.DateUtils;
import com.library.base.widget.TopBar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/08
 *      desc    : 启动页
 *      version : 1.0
 * </pre>
 */
public class SplashActivity extends BaseActivity {
    private TextView dateTv;

    private Disposable disposable;

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    public boolean isSwipeBack() {
        return false;
    }

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        hideTopBar();
    }

    @Override
    public boolean isCanRefresh() {
        return false;
    }

    @Override
    public void doOnRefresh() {

    }

    @Override
    public void initUI(Context context) {
        dateTv = (TextView) findViewById(R.id.date_tv);
    }

    @Override
    public void doBusiness(Context context) {
        dateTv.setText(DateUtils.getCurrentYearToDayToWeek());
        requestRuntimePermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                delayTime();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                delayTime();
            }
        });
    }

    /**
     * 启动页停留时间
     */
    @SuppressLint("CheckResult")
    private void delayTime() {
        Observable
                .timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        ARouterHelper.gotoMain();
                        finish();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
