package com.allen.wanandroid.ui.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.HomeAdapter;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.HomeBean;
import com.allen.wanandroid.constant.ARouterPath;
import com.allen.wanandroid.constant.BundleKey;
import com.allen.wanandroid.ui.presenter.ArticlePresenter;
import com.allen.wanandroid.ui.ui.activity.WebViewActivity;
import com.allen.wanandroid.ui.view.ArticleView;
import com.allen.wanandroid.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    :
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.mainHomePath)
public class HomeFragment extends BaseMvpFragment<ArticlePresenter> implements ArticleView, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener, BGABanner.Delegate {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomeAdapter adapter;

    private List<HomeBean.DatasEntity> datasEntities = new ArrayList<>();

    private int page = 0;

    private BGABanner bgaBanner;

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter();
    }

    @Override
    protected void lazyLoad() {
        showRefreshView();
        mPresenter.getBanner();
        mPresenter.getArticleList(0);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initParams() {

    }

    @Override
    public void initTopBar(TopBar topBar) {
        topBar.isShowLeftImg(false).setCenterText("首页");
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public boolean isCanRefresh() {
        return true;
    }

    @Override
    public void doOnRefresh() {
        page = 0;
        mPresenter.getBanner();
        mPresenter.getArticleList(page);
    }

    @Override
    public void doBusiness(Context context) {

        adapter = new HomeAdapter(datasEntities);
        adapter.addHeaderView(getBannerView());
        adapter.setOnItemClickListener(this);
        adapter.disableLoadMoreIfNotFullPage(recyclerView);
        adapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private View getBannerView() {
        View view = View.inflate(getActivity(), R.layout.adapter_item_header_banner, null);
        bgaBanner = view.findViewById(R.id.banner);
        bgaBanner.setAdapter(new BGABanner.Adapter<ImageView, BannerBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable BannerBean model, int position) {
                GlideUtils.loadImg(getActivity(), model.getImagePath(), itemView);
            }
        });
        bgaBanner.setDelegate(this);
        return view;
    }

    @Override
    public void showBanner(List<BannerBean> list) {

        List<String> tips = new ArrayList<>();
        for (BannerBean bannerBean : list) {
            tips.add(bannerBean.getTitle());
        }
        bgaBanner.setData(list, tips);

    }

    @Override
    public void showNewArticleList(List<HomeBean.DatasEntity> list) {
        datasEntities.clear();
        datasEntities.addAll(list);
        adapter.setNewData(datasEntities);
    }

    @Override
    public void showMoreArticleList(List<HomeBean.DatasEntity> list) {
        datasEntities.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreComplete() {
        adapter.loadMoreComplete();
    }

    @Override
    public void loadMoreEnd() {
        adapter.loadMoreEnd();
    }


    @Override
    public void onLoadMoreRequested() {
        page++;
        mPresenter.getArticleList(page);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.TITLE, datasEntities.get(position).getTitle());
        bundle.putString(BundleKey.URL, datasEntities.get(position).getLink());
        startActivity(WebViewActivity.class, bundle);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
        BannerBean bannerBean = (BannerBean) model;
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.TITLE, bannerBean.getTitle());
        bundle.putString(BundleKey.URL, bannerBean.getUrl());
        startActivity(WebViewActivity.class, bundle);
    }
}
