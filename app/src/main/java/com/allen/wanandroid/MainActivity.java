package com.allen.wanandroid;

import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.wanandroid.constant.ARouterPath;
import com.allen.wanandroid.ui.ui.fragment.CategoryHomeFragment;
import com.allen.wanandroid.ui.ui.fragment.HomeFragment;
import com.allen.wanandroid.ui.ui.fragment.ProjectHomeFragment;
import com.allen.wanandroid.ui.ui.fragment.MineFragment;
import com.allen.wanandroid.utils.DbUtils;
import com.library.base.base.BaseMainActivity;
import com.library.base.bean.Tab;

import java.util.List;

@Route(path = ARouterPath.mainPath)
public class MainActivity extends BaseMainActivity {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        bottomTabView.setCurrentItem(0);
    }

    @Override
    public List<Tab> setBottomTabs(List<Tab> tabs) {
        tabs.add(new Tab("首页", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("项目", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("体系", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("我的", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        return tabs;
    }

    @Override
    public List<String> setFragmentClassNames(List<String> fragmentClassNames) {
        fragmentClassNames.add(HomeFragment.class.getName());
        fragmentClassNames.add(ProjectHomeFragment.class.getName());
        fragmentClassNames.add(CategoryHomeFragment.class.getName());
        fragmentClassNames.add(MineFragment.class.getName());
        return fragmentClassNames;
    }

    @Override
    public boolean isInterceptBeforeSkip() {
        return true;
    }


    @Override
    public void setTabSelectListener(View v, int position) {
        if (position == 3) {
            if (DbUtils.isQueryUser()) {
                showFragment(position);
            } else {
                ARouter.getInstance().build(ARouterPath.loginPath).navigation();
            }
        } else {
            showFragment(position);
        }
    }

}
