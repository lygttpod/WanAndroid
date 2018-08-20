package com.allen.wanandroid.ui.article;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.arouter.ARouterPath;
import com.allen.wanandroid.bean.CategoryBean;
import com.library.base.base.BaseToolBarTabViewPagerActivity;

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
@Route(path = ARouterPath.articleTabViewPagerAcPath)
public class ArticleTabViewPagerActivity extends BaseToolBarTabViewPagerActivity {

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
    public void initToolBar(Toolbar toolBar) {
        if (categoryBean != null) {
            toolBar.setTitle(categoryBean.getName());
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    public List<String> setTabIndicators(List<String> tabIndicators) {
        if (categoryBean != null) {
            for (int i = 0; i < categoryBean.getChildren().size(); i++) {
                tabIndicators.add(categoryBean.getChildren().get(i).getName());
            }
        }
        return tabIndicators;
    }

    @Override
    public List<Fragment> setTabFragment(List<Fragment> tabFragments) {
        if (categoryBean != null) {
            for (int i = 0; i < categoryBean.getChildren().size(); i++) {
                tabFragments.add(ArticleFragment.newInstance(categoryBean.getChildren().get(i).getId()));
            }
        }
        return tabFragments;
    }

    @Override
    public int setCurrentItemIndex() {
        return index;
    }

    @Override
    public int setOffscreenPageLimit() {
        return 3;
    }


    @Override
    public void doBusiness(Context context) {

    }

}
