package com.allen.wanandroid.ui.article;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.CommonFragmentWithTitleAdapter;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.constant.ARouterPath;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/24
 *      desc    : 知识体系下边的Tab列表
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.articleTabViewPagerAcPath)
public class ArticleTabViewPagerActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

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
        return R.layout.activity_article;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        if (categoryBean != null) {
            topBar.setLeftText(categoryBean.getName());
        }
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

        initViewPager();
    }


    private void initViewPager() {

        if (categoryBean != null) {
            for (int i = 0; i < categoryBean.getChildren().size(); i++) {
                tabIndicators.add(categoryBean.getChildren().get(i).getName());
                tabFragments.add(ArticleFragment.newInstance(categoryBean.getChildren().get(i).getId()));
            }
        }

        adapter = new CommonFragmentWithTitleAdapter(getSupportFragmentManager(), tabFragments, tabIndicators);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(index);
        viewPager.setOffscreenPageLimit(3);
    }

}
