package com.library.base.mvp;


/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/05
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface IBaseMvpView {

    void showToast(String msg);

    void showLoading();

    void hideLoading();

}
