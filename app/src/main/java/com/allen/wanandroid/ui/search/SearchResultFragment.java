package com.allen.wanandroid.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.HomeAdapter;
import com.allen.wanandroid.arouter.ARouterHelper;
import com.allen.wanandroid.arouter.ARouterPath;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.HomeBean;
import com.allen.wanandroid.constant.BundleKey;
import com.allen.wanandroid.ui.article.ArticlePresenter;
import com.allen.wanandroid.ui.article.ArticleView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/01
 *      desc    :
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.searchResultFrgPath)
public class SearchResultFragment extends BaseMvpFragment<ArticlePresenter> implements ArticleView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomeAdapter adapter;

    private List<HomeBean.DatasEntity> datasEntities = new ArrayList<>();

    private int page = 0;

    private String keyWord;

    public static SearchResultFragment newInstance(String keyWord) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.TITLE, keyWord);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter();
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public int bindLayout() {
        return R.layout.recycler_view;
    }

    @Override
    public void initParams() {
        keyWord = getArguments().getString(BundleKey.TITLE);
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

        adapter = new HomeAdapter(datasEntities);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        adapter.disableLoadMoreIfNotFullPage(recyclerView);
        adapter.setOnLoadMoreListener(this, recyclerView);
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
        adapter.notifyDataSetChanged();
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

    @Override
    public void onLoadMoreRequested() {
        page++;
        mPresenter.getSearchListByKeyWord(page, keyWord);
    }

    public void setKeyWord(String keyWord) {
        if (TextUtils.isEmpty(keyWord)) {
            return;
        }
        this.keyWord = keyWord;
        mPresenter.getSearchListByKeyWord(page, keyWord);
    }

    public void clearData() {
        keyWord = "";
        datasEntities.clear();
        adapter.notifyDataSetChanged();
    }
}
