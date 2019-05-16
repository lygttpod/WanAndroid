package com.allen.login.login;

import com.allen.common.api.ApiModel;
import com.allen.common.bean.UserBean;
import com.allen.common.utils.DbUtils;
import com.allen.library.observer.DataObserver;
import com.library.base.mvp.BaseMvpPresenter;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/25
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class LoginPresenter extends BaseMvpPresenter<LoginView> {
    private ApiModel apiModel;

    public LoginPresenter() {
        this.apiModel = new ApiModel();
    }

    public void login() {
        String userName = mView.getUserName();
        String password = mView.getPassWord();

        apiModel.login(userName, password, new DataObserver<UserBean>() {
            @Override
            protected void onError(String errorMsg) {
                mView.optionError(errorMsg);
            }

            @Override
            protected void onSuccess(UserBean data) {
                DbUtils.insertUserInfo(data);
                mView.optionSuccess(data);
            }
        });
    }

    public void register() {
        String userName = mView.getUserName();
        String password = mView.getPassWord();
        String rePassword = mView.getRePassWord();

        apiModel.register(userName, password, rePassword, new DataObserver<UserBean>() {
            @Override
            protected void onError(String errorMsg) {
                mView.optionError(errorMsg);
            }

            @Override
            protected void onSuccess(UserBean data) {
                DbUtils.insertUserInfo(data);
                mView.optionSuccess(data);
            }
        });
    }
}