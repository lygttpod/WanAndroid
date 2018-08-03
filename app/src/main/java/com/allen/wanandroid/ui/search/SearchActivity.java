package com.allen.wanandroid.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.CommonFragmentAdapter;
import com.allen.wanandroid.adapter.CommonFragmentWithTitleAdapter;
import com.allen.wanandroid.arouter.ARouterPath;
import com.library.base.base.BaseActivity;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/27
 *      desc    : 搜索页面
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.searchActPath)
public class SearchActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private CommonFragmentWithTitleAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();

    private String keyWord;

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_search;
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
    public void doBusiness(Context context) {
        initSearchView();
        initAdapter();
    }

    private void initAdapter() {
        fragments.add(new HotSearchFragment());
        fragments.add(SearchResultFragment.newInstance(""));
        adapter = new CommonFragmentWithTitleAdapter(getSupportFragmentManager(), fragments, null);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    private void initSearchView() {
        //搜索按钮取消关闭图标的问题
        searchView.onActionViewExpanded();
        //设置搜索输入框是否展开，false展开 true关闭
        searchView.setIconified(false);
        //设置默认搜索内容
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0) {
                    keyWord = query;
                    viewPager.setCurrentItem(1);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    viewPager.setCurrentItem(0);
                }
                return false;
            }
        });

    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        finish();
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
     * @param keyWord 关键字
     */
    public void gotoSearchResult(String keyWord){
        this.keyWord = keyWord;
        searchView.setQuery(keyWord,true);
    }
}
