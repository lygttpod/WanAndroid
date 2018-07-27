package com.allen.wanandroid.ui.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.constant.ARouterPath;
import com.allen.wanandroid.constant.BundleKey;
import com.allen.wanandroid.ui.ui.fragment.ArticleFragment;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/24
 *      desc    :
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.articleListAcPath)
public class ArticleActivity extends BaseActivity {
    private int id;
    private String title;

    @Override
    protected void getBundleData(Bundle params) {
        title = params.getString(BundleKey.TITLE);
        id = params.getInt(BundleKey.ID);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_article;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setLeftText(title);
    }

    @Override
    public boolean isCanRefresh() {
        return false;
    }

    @Override
    public void doOnRefresh() {

    }

    @Override
    public void doBusiness(Context context) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fl, ArticleFragment.newInstance(id));
        ft.commit();
    }
}
