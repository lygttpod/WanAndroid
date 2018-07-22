package com.allen.quickbuildandroid.base;

import android.content.Context;

import com.library.base.mvp.BaseMvpActivity;

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
public abstract class BaseAppMvpActivity extends BaseMvpActivity {

    @Override
    public void initButterKnife(Context context) {
        super.initButterKnife(context);
        ButterKnife.bind(this);
    }
}
