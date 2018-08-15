package com.allen.article.debug;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.allen.article.R;
import com.allen.article.category.CategoryHomeFragment;
import com.allen.article.main.ArticleHomeFragment;
import com.allen.article.project.ProjectHomeFragment;
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
public class ArticleTestFragmentActivity extends BaseActivity {

    private FrameLayout frameLayout;

    public static int type_home = 1;
    public static int type_category = 2;
    public static int type_project = 3;

    private FragmentTransaction ft;

    private int type;

    @Override
    protected void getBundleData(Bundle params) {
        type = params.getInt("type");
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    public int bindLayout() {
        return R.layout.module_article_test_fragment_activity;
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
        frameLayout = (FrameLayout) findViewById(R.id.module_article_test_fragment_fl);
    }

    @Override
    public void doBusiness(Context context) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.module_article_test_fragment_fl, getFragment(type));
        ft.commit();
    }

    private Fragment getFragment(int type) {
        Fragment fragment = null;
        if (type == type_home) {
            fragment = new ArticleHomeFragment();
        } else if (type == type_category) {
            fragment = new CategoryHomeFragment();
        } else if (type == type_project) {
            fragment = new ProjectHomeFragment();
        }

        return fragment;
    }
}
