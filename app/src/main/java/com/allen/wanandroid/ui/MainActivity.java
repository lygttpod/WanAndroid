package com.allen.wanandroid.ui;

import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.article.category.CategoryHomeFragment;
import com.allen.article.main.ArticleHomeFragment;
import com.allen.article.project.ProjectHomeFragment;
import com.allen.common.arouter.ARouterHelper;
import com.allen.common.arouter.ARouterPath;
import com.allen.common.utils.DbUtils;
import com.allen.user.mine.MineHomeFragment;
import com.allen.wanandroid.R;
import com.library.base.base.BaseMainActivity;
import com.library.base.bean.Tab;

import java.util.List;

@Route(path = ARouterPath.mainPath)
public class MainActivity extends BaseMainActivity {


    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        bottomTabView.setCurrentItem(0);
    }

    @Override
    public List<Tab> setBottomTabs(List<Tab> tabs) {
        tabs.add(new Tab("首页", R.mipmap.icon_home_unselected, R.mipmap.icon_home_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("项目", R.mipmap.icon_project_unselected, R.mipmap.icon_project_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("体系", R.mipmap.icon_category_unselected, R.mipmap.icon_category_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        tabs.add(new Tab("我的", R.mipmap.icon_mine_unselected, R.mipmap.icon_mine_selected, R.color.colorTabNormal, R.color.colorTabSelected));
        return tabs;
    }

    @Override
    public List<String> setFragmentClassNames(List<String> fragmentClassNames) {
        fragmentClassNames.add(ArticleHomeFragment.class.getName());
        fragmentClassNames.add(ProjectHomeFragment.class.getName());
        fragmentClassNames.add(CategoryHomeFragment.class.getName());
        fragmentClassNames.add(MineHomeFragment.class.getName());
        return fragmentClassNames;
    }

    @Override
    public boolean isInterceptBeforeSkip(int position) {
//        return false;
        return position == 3 && !DbUtils.isQueryUser();
    }

    @Override
    public void setTabInterceptSkip(View v, final int position) {
        ARouterHelper.login();
    }

}
