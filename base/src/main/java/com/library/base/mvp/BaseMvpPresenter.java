package com.library.base.mvp;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/19
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public abstract class BaseMvpPresenter<V extends IBaseMvpView> implements IBaseMvpPresenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return null != mView;
    }
}
