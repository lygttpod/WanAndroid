package com.allen.article.article;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.article.R;
import com.allen.article.adapter.CommonFragmentWithTitleAdapter;
import com.allen.common.arouter.ARouterPath;
import com.allen.common.bean.CategoryBean;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/24
 *      desc    : 知识体系下边的Tab列表
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.moduleArticleTabViewPagerActivityPath)
public class ArticleTabViewPagerActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> tabIndicators = new ArrayList<>();
    private List<Fragment> tabFragments = new ArrayList<>();

    private CommonFragmentWithTitleAdapter adapter;

    @Autowired
    CategoryBean categoryBean;
    @Autowired
    int index = 0;


    @Override
    protected void getBundleData(Bundle params) {
        categoryBean = params.getParcelable("category");
        index = params.getInt("index");
    }

    @Override
    public int bindLayout() {
        return R.layout.module_article_activity_article;
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
        initToolBar();
        initViewPager();
    }

    private void initToolBar() {
        if (categoryBean != null) {
            mToolbar.setTitle(categoryBean.getName());
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }


    private void initViewPager() {

        if (categoryBean != null) {
            for (int i = 0; i < categoryBean.getChildren().size(); i++) {
                tabIndicators.add(categoryBean.getChildren().get(i).getName());
                tabFragments.add(ArticleFragment.newInstance(categoryBean.getChildren().get(i).getId()));
            }
        }

        adapter = new CommonFragmentWithTitleAdapter(getSupportFragmentManager(), tabFragments, tabIndicators);
        mViewPager.setAdapter(adapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(index);
        mViewPager.setOffscreenPageLimit(3);
    }

    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }
}
