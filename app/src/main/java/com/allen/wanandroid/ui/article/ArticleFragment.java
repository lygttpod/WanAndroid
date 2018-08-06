package com.allen.wanandroid.ui.article;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.HomeAdapter;
import com.allen.wanandroid.arouter.ARouterHelper;
import com.allen.wanandroid.arouter.ARouterPath;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.HomeBean;
import com.allen.wanandroid.constant.BundleKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    : 知识体系下边的文章列表
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.articleListFrPath)
public class ArticleFragment extends BaseMvpFragment<ArticlePresenter> implements ArticleView, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomeAdapter adapter;

    private List<HomeBean.DatasEntity> datasEntities = new ArrayList<>();

    private int page = 0;

    private int id;

    public static ArticleFragment newInstance(int id) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter();
    }

    @Override
    protected void lazyLoad() {
        showRefreshView();
        mPresenter.getCategoryArticleListWithId(0, id);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initParams() {
        id = getArguments().getInt(BundleKey.ID);
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
        return true;
    }

    @Override
    public void doOnRefresh() {
        page = 0;
        mPresenter.getCategoryArticleListWithId(page, id);
    }

    @Override
    public void doBusiness(Context context) {

        adapter = new HomeAdapter(datasEntities);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.disableLoadMoreIfNotFullPage(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void showBanner(List<BannerBean> list) {

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
    public void collectSuccess(int position, String msg) {
        datasEntities.get(position).setCollect(true);
        adapter.notifyItemChanged(position, 1);
        showToast(msg);
    }

    @Override
    public void cancelCollectSuccess(int position, String msg) {
        datasEntities.get(position).setCollect(false);
        adapter.notifyItemChanged(position, 1);
        showToast(msg);
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
    public void enableLoadMore(boolean enable) {
        adapter.setEnableLoadMore(enable);
    }


    @Override
    public void onLoadMoreRequested() {
        page++;
        mPresenter.getCategoryArticleListWithId(page, id);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ARouterHelper.gotoWebViewActivity(datasEntities.get(position).getTitle(), datasEntities.get(position).getLink());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (datasEntities.get(position).isCollect()) {
            mPresenter.cancelCollectArticleById(datasEntities.get(position).getId(), position);
        } else {
            mPresenter.collectArticleById(datasEntities.get(position).getId(), position);
        }
    }
}
