package com.library.base.base;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.library.base.R;
import com.library.base.adapter.CommonFragmentAdapter;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/16
 *      desc    : ToolBar+tablayout_viewpager封装
 *      version : 1.0
 * </pre>
 */
public abstract class BaseToolBarTabViewPagerActivity extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private CommonFragmentAdapter adapter;

    public abstract void initToolBar(Toolbar toolBar);

    public void initTabLayout(TabLayout tabLayout) {
    }

    public void initViewPager(ViewPager viewPager) {
    }

    public abstract List<String> setTabIndicators(List<String> tabIndicators);

    public abstract List<Fragment> setTabFragment(List<Fragment> tabFragments);

    public abstract int setCurrentItemIndex();

    public abstract int setOffscreenPageLimit();


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
    public int bindLayout() {
        return R.layout.activity_base_toolbar_tab_viewpager;
    }

    @Override
    public void initUI(Context context) {

        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        tabLayout = (TabLayout) findViewById(R.id.base_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.base_view_pager);

        initToolBar(toolbar);
        initTabLayout(tabLayout);
        initViewPager(viewPager);

        setViewPager();

    }

    private void setViewPager() {
        adapter = new CommonFragmentAdapter(getSupportFragmentManager(), setTabFragment(new ArrayList<Fragment>()), setTabIndicators(new ArrayList<String>()));
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(setCurrentItemIndex());
        viewPager.setOffscreenPageLimit(setOffscreenPageLimit());
    }

}
