package com.allen.article.search;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.allen.article.R;
import com.allen.article.adapter.TagFlowAdapter;
import com.allen.common.bean.HotBean;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/03
 *      desc    : 热门搜索
 *      version : 1.0
 * </pre>
 */
public class HotSearchFragment extends BaseMvpFragment<SearchPresenter> implements SearchViewView, TagFlowLayout.OnTagClickListener {

    /**
     * 搜索热词
     */
    private TextView mHotTv;
    private TagFlowLayout mFlowHotLayout;
    /**
     * 常用网站
     */
    private TextView mWebSiteTv;
    private TagFlowLayout mFlowWebSiteLayout;

    private TagAdapter<HotBean> hotSearchTagAdapter;
    private TagAdapter<HotBean> webSiteTagAdapter;

    private List<HotBean> hotBeans = new ArrayList<>();
    private List<HotBean> webSiteBeans = new ArrayList<>();


    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public int bindLayout() {
        return R.layout.module_article_fragment_hot_search;
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

        mHotTv = view.findViewById(R.id.hot_tv);
        mFlowHotLayout = view.findViewById(R.id.flow_hot_layout);
        mWebSiteTv = view.findViewById(R.id.web_site_tv);
        mFlowWebSiteLayout = view.findViewById(R.id.flow_web_site_layout);
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
        initTagFlow();
        mPresenter.getHotSearchData();
        mPresenter.getWebSiteData();
    }

    private void initTagFlow() {
        mFlowHotLayout.setOnTagClickListener(this);
        mFlowWebSiteLayout.setOnTagClickListener(this);

        hotSearchTagAdapter = new TagFlowAdapter(getActivity(), hotBeans);
        webSiteTagAdapter = new TagFlowAdapter(getActivity(), webSiteBeans);

        mFlowHotLayout.setAdapter(hotSearchTagAdapter);
        mFlowWebSiteLayout.setAdapter(webSiteTagAdapter);
    }


    @Override
    public void setHotSearchData(List<HotBean> hotSearchData) {
        setTagData(hotSearchData, mHotTv, hotBeans, hotSearchTagAdapter);
    }

    @Override
    public void setWebSiteData(List<HotBean> webSiteData) {
        setTagData(webSiteData, mWebSiteTv, webSiteBeans, webSiteTagAdapter);
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        if (parent.getId() == R.id.flow_hot_layout) {
            SearchActivity searchActivity = (SearchActivity) getActivity();
            searchActivity.gotoSearchResult(hotBeans.get(position).getName());
        } else if (parent.getId() == R.id.flow_web_site_layout) {
            mPresenter.gotoWebSiteDetail(webSiteBeans.get(position).getName(), webSiteBeans.get(position).getLink());
        }
        return true;
    }

    private void setTagData(List<HotBean> data, TextView textView, List<HotBean> beans, TagAdapter<HotBean> adapter) {
        if (data.size() > 0) {
            textView.setVisibility(View.VISIBLE);
            beans.clear();
            beans.addAll(data);
            adapter.notifyDataChanged();
        }
    }


}
