package com.allen.article.project;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.allen.article.R;
import com.allen.article.adapter.CommonFragmentWithTitleAdapter;
import com.allen.common.bean.CategoryBean;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/27
 *      desc    : 项目列表
 *      version : 1.0
 * </pre>
 */
public class ProjectHomeFragment extends BaseMvpFragment<ProjectHomePresenter> implements ProjectHomeView {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<String> tabIndicators = new ArrayList<>();
    private List<Fragment> tabFragments = new ArrayList<>();

    private CommonFragmentWithTitleAdapter adapter;


    @Override
    protected int setStatusBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected ProjectHomePresenter createPresenter() {
        return new ProjectHomePresenter();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_article_fragment_project;
    }

    @Override
    public void initParams() {

    }

    @Override
    public void initTopBar(TopBar topBar) {
        hideTopBar();
    }

    @Override
    public void initView(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
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
        mPresenter.getProjectTabData();
    }

    private void initViewPager() {
        adapter = new CommonFragmentWithTitleAdapter(getChildFragmentManager(), tabFragments, tabIndicators);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void setTabData(List<CategoryBean> tabs) {
        for (int i = 0; i < tabs.size(); i++) {
            tabIndicators.add(tabs.get(i).getName());
            tabFragments.add(ProjectArticleFragment.newInstance(tabs.get(i).getId()));
        }
        initViewPager();
    }

}
