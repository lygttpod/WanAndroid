package com.allen.wanandroid.ui.view;

import com.library.base.mvp.IBaseMvpView;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface MeView extends IBaseMvpView {
    void setUserName(String userName);

    void gotoCollectList();

    void gotoHistory();

    void aboutMe();

    void logout();

}
