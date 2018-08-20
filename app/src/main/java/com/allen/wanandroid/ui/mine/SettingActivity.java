package com.allen.wanandroid.ui.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.RxHttpUtils;
import com.allen.library.SuperTextView;
import com.allen.wanandroid.R;
import com.allen.wanandroid.arouter.ARouterHelper;
import com.allen.wanandroid.arouter.ARouterPath;
import com.allen.wanandroid.bean.UserBean;
import com.allen.wanandroid.utils.DbUtils;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/08/05
 *      desc    : 设置中心
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.settingActPath)
public class SettingActivity extends BaseActivity {
    @BindView(R.id.night_stv)
    SuperTextView nightStv;
    @BindView(R.id.slide_back_stv)
    SuperTextView slideBackStv;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
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

    }

    @Override
    public void doBusiness(Context context) {
        nightStv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                showToastMsg("敬请期待...");
                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
            }
        });

        slideBackStv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                showToastMsg("敬请期待...");
                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked());
            }
        });
    }

    @OnClick({R.id.logout_sbt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logout_sbt:
                logout();
                break;
        }
    }

    public void logout() {
        RxHttpUtils.removeAllCookie();
        UserBean userBean = DbUtils.queryUserInfo();
        if (null != userBean) {
            DbUtils.deleteUserInfo(userBean);
        }
        ARouterHelper.gotoMain();
    }

}
