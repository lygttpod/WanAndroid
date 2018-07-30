package com.allen.wanandroid.ui.collect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.HomeAdapter;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.HomeBean;
import com.allen.wanandroid.constant.ARouterPath;
import com.allen.wanandroid.constant.BundleKey;
import com.allen.wanandroid.ui.article.ArticlePresenter;
import com.allen.wanandroid.ui.webview.WebViewActivity;
import com.allen.wanandroid.ui.article.ArticleView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.library.base.mvp.BaseMvpActivity;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    :
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.userCollectAcPath)
public class UserCollectActivity extends BaseMvpActivity<ArticlePresenter> implements ArticleView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomeAdapter adapter;

    private List<HomeBean.DatasEntity> datasEntities = new ArrayList<>();

    private int page = 0;

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter();
    }

    @Override
    protected void getBundleData(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setTopBar(TopBar topBar) {
        topBar.setCenterText("我的收藏");

    }

    @Override
    public boolean isCanRefresh() {
        return true;
    }

    @Override
    public void doOnRefresh() {
        page = 0;
        mPresenter.getUserCollectList(page);
    }

    @Override
    public void doBusiness(Context context) {

        adapter = new HomeAdapter(datasEntities);
        adapter.setOnItemClickListener(this);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.disableLoadMoreIfNotFullPage(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        mPresenter.getUserCollectList(0);
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoading() {
        hideRefreshView();
    }


    @Override
    public void showBanner(List<BannerBean> list) {
    }

    @Override
    public void showNewArticleList(List<HomeBean.DatasEntity> list) {
        datasEntities.clear();
        datasEntities.addAll(list);
        adapter.notifyDataSetChanged();
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
        mPresenter.getUserCollectList(page);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.TITLE, datasEntities.get(position).getTitle());
        bundle.putString(BundleKey.URL, datasEntities.get(position).getLink());
        startActivity(WebViewActivity.class, bundle);
    }
}
