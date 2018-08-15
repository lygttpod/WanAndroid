package com.allen.login.debug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.common.arouter.ARouterPath;
import com.allen.common.bean.UserBean;
import com.allen.common.utils.DbUtils;
import com.allen.library.RxHttpUtils;
import com.allen.login.R;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/15
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class LoginTestMainActivity extends BaseActivity implements View.OnClickListener {

    private Button loginBtn, logoutBtn;
    private TextView userInfoTv;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_login_activity_test_main;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setCenterText("登录模块测试");
    }

    @Override
    public boolean isCanRefresh() {
        return false;
    }

    @Override
    public void doOnRefresh() {

    }

    @Override
    public void initUI(Context context) {
        loginBtn = (Button) findViewById(R.id.module_login_login_btn);
        logoutBtn = (Button) findViewById(R.id.module_login_logout_btn);
        userInfoTv = (TextView) findViewById(R.id.module_login_user_info_tv);
        loginBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context context) {
        getUserData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.module_login_login_btn) {
            ARouter
                    .getInstance()
                    .build(ARouterPath.moduleLoginLoginActivityPath)
                    .navigation(this, 100);
        } else if (v.getId() == R.id.module_login_logout_btn) {
            logout();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            getUserData();
        }
    }

    private void getUserData() {
        UserBean userBean = DbUtils.queryUserInfo();
        if (userBean != null) {
            userInfoTv.setText(userBean.toString());
        } else {
            userInfoTv.setText("");

        }
    }

    public void logout() {
        RxHttpUtils.removeAllCookie();
        UserBean userBean = DbUtils.queryUserInfo();
        if (null != userBean) {
            DbUtils.deleteUserInfo(userBean);
        }
        getUserData();
    }
}
