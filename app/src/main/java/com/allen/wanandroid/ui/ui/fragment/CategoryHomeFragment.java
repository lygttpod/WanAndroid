package com.allen.wanandroid.ui.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.CategoryHomeAdapter;
import com.allen.wanandroid.ui.ui.activity.ArticleActivity;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.constant.BundleKey;
import com.allen.wanandroid.ui.presenter.CategoryHomePresenter;
import com.allen.wanandroid.ui.view.CategoryHomeView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.SuperDividerItemDecoration;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.allen.wanandroid.adapter.CategoryHomeAdapter.TYPE_TWO;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/23
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class CategoryHomeFragment extends BaseMvpFragment<CategoryHomePresenter> implements CategoryHomeView, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CategoryHomeAdapter adapter;

    private List<MultiItemEntity> listBeans = new ArrayList<>();

    @Override
    protected CategoryHomePresenter createPresenter() {
        return new CategoryHomePresenter();
    }

    @Override
    protected void lazyLoad() {

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
        adapter = new CategoryHomeAdapter(listBeans);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SuperDividerItemDecoration.Builder(context).build());
        recyclerView.setAdapter(adapter);

        mPresenter.getCategoryData();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showCategoryList(List<CategoryBean> list) {
        listBeans.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int childrenSize = list.get(i).getChildren().size();
            CategoryBean.TagBean tagBean = new CategoryBean.TagBean();
            CategoryBean categoryBean = list.get(i);
            tagBean.setCourseId(categoryBean.getCourseId());
            tagBean.setId(categoryBean.getId());
            tagBean.setName(categoryBean.getName());
            tagBean.setOrder(categoryBean.getOrder());
            tagBean.setParentChapterId(categoryBean.getParentChapterId());
            tagBean.setVisible(categoryBean.getVisible());
            for (int j = 0; j < childrenSize; j++) {
                CategoryBean.ChildrenBean childrenBean = list.get(i).getChildren().get(j);
                tagBean.addSubItem(childrenBean);
            }
            listBeans.add(tagBean);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (listBeans.get(position).getItemType() == TYPE_TWO) {
            CategoryBean.ChildrenBean childrenBean = (CategoryBean.ChildrenBean) listBeans.get(position);
            Bundle bundle = new Bundle();
            bundle.putInt(BundleKey.ID, childrenBean.getId());
            bundle.putString(BundleKey.TITLE, childrenBean.getName());
            startActivity(ArticleActivity.class, bundle);
        }
    }
}
