package com.allen.article.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.article.R;
import com.allen.article.adapter.CommonFragmentWithTitleAdapter;
import com.allen.article.widget.CustomViewPager;
import com.allen.common.arouter.ARouterPath;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/27
 *      desc    : 搜索页面
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.moduleArticleSearchActivityPath)
public class SearchActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ImageView mBackIv;
    private SearchView mSearchView;
    private CustomViewPager mViewPager;

    private CommonFragmentWithTitleAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();

    private String keyWord;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.module_article_fragment_search;
    }

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
    public void initUI(Context context) {
        initView();
    }

    @Override
    public void doBusiness(Context context) {
        initSearchView();
        initAdapter();
    }

    private void initAdapter() {
        fragments.add(new HotSearchFragment());
        fragments.add(SearchResultFragment.newInstance(""));
        adapter = new CommonFragmentWithTitleAdapter(getSupportFragmentManager(), fragments, null);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    private void initSearchView() {
        //搜索按钮取消关闭图标的问题
        mSearchView.onActionViewExpanded();
        //设置搜索输入框是否展开，false展开 true关闭
        mSearchView.setIconified(false);
        //设置默认搜索内容
        SearchView.SearchAutoComplete searchAutoComplete = mSearchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.white));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0) {
                    keyWord = query;
                    mViewPager.setCurrentItem(1);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    mViewPager.setCurrentItem(0);
                }
                return false;
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                keyWord = "";
                ((SearchResultFragment) fragments.get(1)).clearData();
                break;
            case 1:
                ((SearchResultFragment) fragments.get(1)).setKeyWord(keyWord);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 热搜页面调取activity的方法传值给搜索结果页面
     *
     * @param keyWord 关键字
     */
    public void gotoSearchResult(String keyWord) {
        this.keyWord = keyWord;
        mSearchView.setQuery(keyWord, true);
    }

    public void initView() {
        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mBackIv.setOnClickListener(this);
        mSearchView = (SearchView) findViewById(R.id.search_view);
        mViewPager = (CustomViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
