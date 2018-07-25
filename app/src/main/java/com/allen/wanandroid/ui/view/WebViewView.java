package com.allen.wanandroid.ui.view;

import android.widget.ProgressBar;

import com.library.base.mvp.IBaseMvpView;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/25
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface WebViewView extends IBaseMvpView{

    ProgressBar getProgressBar();

    void setTitle(String title);
}
