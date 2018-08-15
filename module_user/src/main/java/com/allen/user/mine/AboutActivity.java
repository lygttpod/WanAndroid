package com.allen.user.mine;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.common.arouter.ARouterPath;
import com.allen.user.BuildConfig;
import com.allen.user.R;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/03
 *      desc    : 关于我们
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.moduleUserAboutActivityPath)
public class AboutActivity extends BaseActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mVersionTv;
    private TextView mAboutTv;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_user_activity_about;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        hideTopBar();
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
        mVersionTv.setText(BuildConfig.VERSION_NAME);
        //设置展开后标题的颜色
        mToolbarLayout.setExpandedTitleColor(Color.WHITE);
        //设置收缩后标题的颜色
        mToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAboutTv.setText(Html.fromHtml(getResources().getString(R.string.module_user_about_content)));
    }


    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mVersionTv = (TextView) findViewById(R.id.version_tv);
        mAboutTv = (TextView) findViewById(R.id.about_tv);
    }
}
