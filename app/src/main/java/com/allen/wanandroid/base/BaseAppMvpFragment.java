package com.allen.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.library.base.mvp.BaseMvpFragment;
import com.library.base.mvp.BaseMvpPresenter;
import com.library.base.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/22
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public abstract class BaseAppMvpFragment<P extends BaseMvpPresenter> extends BaseMvpFragment<P> {
    public LoadingDialog loadingDialog;
    Unbinder unbinder;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog(getActivity());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initButterKnife(Object target, View view) {
        super.initButterKnife(target, view);
       unbinder =  ButterKnife.bind(target, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null!=unbinder){
            unbinder.unbind();
        }
    }

    public LoadingDialog getLoadingDialog() {
        return loadingDialog;
    }
}
