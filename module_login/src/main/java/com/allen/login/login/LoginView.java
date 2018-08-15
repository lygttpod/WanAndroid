package com.allen.login.login;

import com.allen.common.bean.UserBean;
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
public interface LoginView extends IBaseMvpView {

    String getUserName();

    String getPassWord();

    String getRePassWord();

    void optionSuccess(UserBean userBean);

    void optionError(String error);
}
