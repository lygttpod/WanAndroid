package com.allen.quickbuildandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.library.base.base.BaseFragment;
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
public abstract class BaseAppFragment extends BaseFragment {
    Unbinder unbinder;
    private LoadingDialog loadingDialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog(getActivity());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initButterKnife(Object target, View view) {
        super.initButterKnife(target, view);
        unbinder = ButterKnife.bind(target, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != unbinder) {
            unbinder.unbind();
        }
    }

}
