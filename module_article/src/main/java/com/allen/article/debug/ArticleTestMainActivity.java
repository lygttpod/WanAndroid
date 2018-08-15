package com.allen.article.debug;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.allen.article.R;
import com.allen.common.arouter.ARouterHelper;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/15
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class ArticleTestMainActivity extends BaseActivity implements View.OnClickListener {


    /**
     * 首页
     */
    private Button mModuleArticleTestHome;
    /**
     * 项目
     */
    private Button mModuleArticleTestProject;
    /**
     * 知识体系
     */
    private Button mModuleArticleTestCategory;
    /**
     * 我的收藏
     */
    private Button mModuleArticleTestCollect;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_article_test_main_activity;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setCenterText("测试文章模块");
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

    }


    public void initView() {
        mModuleArticleTestHome = (Button) findViewById(R.id.module_article_test_home);
        mModuleArticleTestHome.setOnClickListener(this);
        mModuleArticleTestProject = (Button) findViewById(R.id.module_article_test_project);
        mModuleArticleTestProject.setOnClickListener(this);
        mModuleArticleTestCategory = (Button) findViewById(R.id.module_article_test_category);
        mModuleArticleTestCategory.setOnClickListener(this);
        mModuleArticleTestCollect = (Button) findViewById(R.id.module_article_test_collect);
        mModuleArticleTestCollect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        if (v.getId() == R.id.module_article_test_home) {
            bundle.putInt("type", ArticleTestFragmentActivity.type_home);
            startActivity(ArticleTestFragmentActivity.class, bundle);
        } else if (v.getId() == R.id.module_article_test_category) {
            bundle.putInt("type", ArticleTestFragmentActivity.type_category);
            startActivity(ArticleTestFragmentActivity.class, bundle);
        } else if (v.getId() == R.id.module_article_test_project) {
            bundle.putInt("type", ArticleTestFragmentActivity.type_project);
            startActivity(ArticleTestFragmentActivity.class, bundle);
        } else if (v.getId() == R.id.module_article_test_collect) {
            ARouterHelper.gotoCollectList();
        }
    }
}
