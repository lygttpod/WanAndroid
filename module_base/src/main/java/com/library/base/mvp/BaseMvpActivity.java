package com.library.base.mvp;

import android.app.Dialog;

import com.library.base.base.BaseActivity;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/17
 *      desc    : MVP activity 基类
 *      version : 1.0
 * </pre>
 */
public abstract class BaseMvpActivity<P extends BaseMvpPresenter> extends BaseActivity implements IBaseMvpView {

    protected P mPresenter;

    protected abstract P createPresenter();

    @SuppressWarnings("unchecked")
    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        showToastMsg(msg);
    }

    @Override
    public Dialog getLoadingDialog() {
        return loadingDialog;
    }

    @Override
    public void showLoading() {
        showRefreshView();
    }

    @Override
    public void hideLoading() {
        hideRefreshView();
    }
}
