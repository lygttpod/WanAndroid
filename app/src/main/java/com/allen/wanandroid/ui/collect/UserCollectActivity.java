package com.allen.wanandroid.ui.collect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.common.arouter.ARouterHelper;
import com.allen.common.arouter.ARouterPath;
import com.allen.common.bean.CollectBean;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.CollectAdapter;
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
 *      desc    : 用户收藏
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.userCollectAcPath)
public class UserCollectActivity extends BaseMvpActivity<CollectPresenter> implements CollectView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CollectAdapter adapter;

    private List<CollectBean.DatasBean> datasBeans = new ArrayList<>();

    private int page = 0;

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter();
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

        adapter = new CollectAdapter(datasBeans);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
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
    public void showNewArticleList(List<CollectBean.DatasBean> list) {
        datasBeans.clear();
        datasBeans.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreArticleList(List<CollectBean.DatasBean> list) {
        datasBeans.addAll(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void cancelCollectSuccess(int position, String msg) {
        datasBeans.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, datasBeans.size());
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
        mPresenter.getUserCollectList(page);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ARouterHelper.gotoWebViewActivity(datasBeans.get(position).getTitle(), datasBeans.get(position).getLink());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.cancelUserCollectArticleById(datasBeans.get(position).getId(), datasBeans.get(position).getOriginId(), position);
    }
}
