package com.allen.wanandroid.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.CategoryHomeAdapter;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.constant.ARouterPath;
import com.allen.wanandroid.ui.article.ArticleTabViewPagerActivity;
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
 *      date    : 2018/07/23
 *      desc    :
 *      version : 1.0
 * </pre>
 */
@Route(path = ARouterPath.categoryPath)
public class CategoryHomeFragment extends BaseMvpFragment<CategoryHomePresenter> implements CategoryHomeView, BaseQuickAdapter.OnItemClickListener, CategoryHomeAdapter.onTagItemClick {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CategoryHomeAdapter adapter;

    private List<CategoryBean> listBeans = new ArrayList<>();

    @Override
    protected CategoryHomePresenter createPresenter() {
        return new CategoryHomePresenter();
    }

    @Override
    protected void lazyLoad() {
        showRefreshView();
        mPresenter.getCategoryData();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_category_home;
    }

    @Override
    public void initParams() {

    }

    @Override
    public void initTopBar(TopBar topBar) {
        topBar.isShowLeftImg(false).setCenterText("知识体系");

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
        adapter = new CategoryHomeAdapter(context, listBeans);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showCategoryList(List<CategoryBean> list) {
        listBeans.clear();
        listBeans.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CategoryBean category = listBeans.get(position);
        startArticleTabViewPagerAct(category, 0);

    }

    @Override
    public void OnTagClickListener(CategoryBean categoryBean, int position) {
        startArticleTabViewPagerAct(categoryBean, position);
    }

    private void startArticleTabViewPagerAct(CategoryBean bean, int index) {
        ARouter
                .getInstance()
                .build(ARouterPath.articleTabViewPagerAcPath)
                .withInt("index",index)
                .withParcelable("category",bean)
                .navigation();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("category", bean);
//        bundle.putInt("index", index);
//        startActivity(ArticleTabViewPagerActivity.class, bundle);
    }
}
