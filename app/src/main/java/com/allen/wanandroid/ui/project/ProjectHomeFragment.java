package com.allen.wanandroid.ui.project;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.allen.common.bean.CategoryBean;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.CommonFragmentWithTitleAdapter;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

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
        return R.layout.fragment_project;
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
