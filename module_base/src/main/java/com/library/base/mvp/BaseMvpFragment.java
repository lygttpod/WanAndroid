package com.library.base.mvp;

import android.app.Dialog;

import com.library.base.base.BaseFragment;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/20
 *      desc    : MVP fragment 基类
 *      version : 1.0
 * </pre>
 */
public abstract class BaseMvpFragment<P extends BaseMvpPresenter> extends BaseFragment implements IBaseMvpView {

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
    public void onDestroyView() {
        super.onDestroyView();
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
