package com.allen.user.debug;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.allen.common.arouter.ARouterHelper;
import com.allen.user.R;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/14
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class UserMainActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 登录/注册
     */
    private Button mLoginBt;
    /**
     * 我的
     */
    private Button mMineBt;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_user_test_main;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setCenterText("模块化测试");
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
        initView();
    }

    @Override
    public void doBusiness(Context context) {

    }


    public void initView() {
        mLoginBt = (Button) findViewById(R.id.login_bt);
        mLoginBt.setOnClickListener(this);
        mMineBt = (Button) findViewById(R.id.mine_bt);
        mMineBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_bt) {
            ARouterHelper.login();
        } else if (v.getId() == R.id.mine_bt) {
            startActivity(UserMineActivity.class);
        }
    }
}
