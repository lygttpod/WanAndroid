package com.allen.quickbuildandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.allen.quickbuildandroid.app.App;
import com.library.base.base.BaseActivity;
import com.library.base.widget.LoadingDialog;

import butterknife.ButterKnife;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/22
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public abstract class BaseAppActivity extends BaseActivity {
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initButterKnife(Context context) {
        super.initButterKnife(context);
        ButterKnife.bind(this);
    }

    public void showLoading() {
        if (null != loadingDialog) {
            if (!isFinishing()) {
                loadingDialog.show();
            }
        }
    }

    public void dismissLoading() {
        if (null != loadingDialog) {
            if (!isFinishing()) {
                loadingDialog.dismiss();
            }
        }
    }

}
