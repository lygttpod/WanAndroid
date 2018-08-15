package com.allen.user.mine;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.common.arouter.ARouterHelper;
import com.allen.common.arouter.ARouterPath;
import com.allen.common.bean.UserBean;
import com.allen.common.utils.DbUtils;
import com.allen.library.RxHttpUtils;
import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.allen.user.R;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/08/05
 *      desc    : 设置中心
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.moduleUserSettingActivityPath)
public class SettingActivity extends BaseActivity {

    private SuperTextView mNightStv;
    private SuperTextView mSlideBackStv;
    /**
     * 退出登录
     */
    private SuperButton mLogoutSbt;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_user_activity_setting;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setCenterText("设置");
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
        mNightStv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                showToastMsg("敬请期待...");
                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
            }
        });

        mSlideBackStv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                showToastMsg("敬请期待...");
                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
            }
        });
    }


    public void logout() {
        RxHttpUtils.removeAllCookie();
        UserBean userBean = DbUtils.queryUserInfo();
        if (null != userBean) {
            DbUtils.deleteUserInfo(userBean);
        }
        ARouterHelper.gotoMain();
    }


    public void initView() {
        mNightStv = (SuperTextView) findViewById(R.id.night_stv);
        mSlideBackStv = (SuperTextView) findViewById(R.id.slide_back_stv);
        mLogoutSbt = (SuperButton) findViewById(R.id.logout_sbt);
    }
}
