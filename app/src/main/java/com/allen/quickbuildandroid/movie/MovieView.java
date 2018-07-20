package com.allen.quickbuildandroid.movie;

import com.library.base.mvp.IBaseMvpView;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/20
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface MovieView extends IBaseMvpView {

    void showMovieData();

    void showError(String errorMsg);
}
