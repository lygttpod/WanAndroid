package com.allen.quickbuildandroid;

import android.view.View;

import com.allen.quickbuildandroid.movie.MovieFragment;
import com.library.base.base.BaseMainActivity;
import com.library.base.bean.Tab;

import java.util.List;

public class MainActivity extends BaseMainActivity {

    @Override
    public List<Tab> setBottomTabs(List<Tab> tabs) {
        tabs.add(new Tab("首页", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("首页", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("首页", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("首页", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        return tabs;
    }

    @Override
    public List<String> setFragmentClassNames(List<String> fragmentClassNames) {
        fragmentClassNames.add(MovieFragment.class.getName());
        fragmentClassNames.add(MovieFragment.class.getName());
        fragmentClassNames.add(MovieFragment.class.getName());
        return fragmentClassNames;
    }


    @Override
    public void setTabSelectListener(View v, int position) {

    }

    @Override
    public void doBusiness() {

    }
}
