package com.allen.wanandroid;

import android.view.View;

import com.allen.wanandroid.home.HomeFragment;
import com.allen.wanandroid.movie.MovieFragment;
import com.library.base.base.BaseMainActivity;
import com.library.base.bean.Tab;

import java.util.List;

public class MainActivity extends BaseMainActivity {

    @Override
    public List<Tab> setBottomTabs(List<Tab> tabs) {
        tabs.add(new Tab("首页", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("热搜", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("体系", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("我的", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        return tabs;
    }

    @Override
    public List<String> setFragmentClassNames(List<String> fragmentClassNames) {
        fragmentClassNames.add(HomeFragment.class.getName());
        fragmentClassNames.add(HomeFragment.class.getName());
        fragmentClassNames.add(HomeFragment.class.getName());
        fragmentClassNames.add(HomeFragment.class.getName());
        return fragmentClassNames;
    }

    @Override
    public boolean isInterceptBeforeSkip() {
        return false;
    }


    @Override
    public void setTabSelectListener(View v, int position) {
    }

    @Override
    public void doBusiness() {

    }
}
