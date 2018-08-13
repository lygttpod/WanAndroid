package com.allen.wanandroid.ui.search;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.allen.common.bean.HotBean;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.TagFlowAdapter;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    @BindView(R.id.hot_tv)
    TextView hotTv;
    @BindView(R.id.web_site_tv)
    TextView webSiteTv;
    @BindView(R.id.flow_hot_layout)
    TagFlowLayout flowHotLayout;
    @BindView(R.id.flow_web_site_layout)
    TagFlowLayout flowWebSiteLayout;


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
        return R.layout.fragment_hot_search;
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
        flowHotLayout.setOnTagClickListener(this);
        flowWebSiteLayout.setOnTagClickListener(this);

        hotSearchTagAdapter = new TagFlowAdapter(getActivity(), hotBeans);
        webSiteTagAdapter = new TagFlowAdapter(getActivity(), webSiteBeans);

        flowHotLayout.setAdapter(hotSearchTagAdapter);
        flowWebSiteLayout.setAdapter(webSiteTagAdapter);
    }


    @Override
    public void setHotSearchData(List<HotBean> hotSearchData) {
        setTagData(hotSearchData, hotTv, hotBeans, hotSearchTagAdapter);
    }

    @Override
    public void setWebSiteData(List<HotBean> webSiteData) {
        setTagData(webSiteData, webSiteTv, webSiteBeans, webSiteTagAdapter);
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        switch (parent.getId()) {
            case R.id.flow_hot_layout:
                SearchActivity searchActivity = (SearchActivity) getActivity();
                searchActivity.gotoSearchResult(hotBeans.get(position).getName());
                break;
            case R.id.flow_web_site_layout:
                mPresenter.gotoWebSiteDetail(webSiteBeans.get(position).getName(), webSiteBeans.get(position).getLink());
                break;
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
