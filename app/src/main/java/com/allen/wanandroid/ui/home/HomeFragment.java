package com.allen.wanandroid.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.common.arouter.ARouterHelper;
import com.allen.common.arouter.ARouterPath;
import com.allen.common.bean.BannerBean;
import com.allen.common.bean.HomeBean;
import com.allen.common.utils.DisplayUtils;
import com.allen.common.utils.GlideUtils;
import com.allen.wanandroid.R;
import com.allen.wanandroid.adapter.HomeAdapter;
import com.allen.wanandroid.ui.article.ArticlePresenter;
import com.allen.wanandroid.ui.article.ArticleView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.library.base.mvp.BaseMvpFragment;
import com.library.base.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
public class HomeFragment extends BaseMvpFragment<ArticlePresenter> implements ArticleView, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener, BGABanner.Delegate, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.status_bar_view)
    View statusBarView;

    @BindView(R.id.title_bg_rl)
    RelativeLayout topBarBgRl;

    @BindView(R.id.search_iv)
    ImageView searchIv;

    @BindView(R.id.title_tv)
    TextView titleTv;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomeAdapter adapter;

    private List<HomeBean.DatasEntity> datasEntities = new ArrayList<>();

    private int page = 0;

    private BGABanner bgaBanner;

    private LinearLayoutManager layoutManager;

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
        mPresenter.getBanner();
        mPresenter.getArticleList(page);
    }

    @Override
    public void doBusiness(Context context) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = getStatusBarHeight(getActivity());
        statusBarView.setLayoutParams(layoutParams);

        adapter = new HomeAdapter(datasEntities);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.addHeaderView(getBannerView());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        adapter.disableLoadMoreIfNotFullPage(recyclerView);
        adapter.setOnLoadMoreListener(this, recyclerView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int imageHeight = DisplayUtils.dp2px(150);

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                float scale = getScaleDistance(0, imageHeight);

                if (scale <= 0) {
                    setColorChange(0);
                } else if (scale >= 1) {
                    setColorChange(1);
                } else {
                    setColorChange(scale);
                }
            }
        });
    }

    /**
     * 动态改变状态栏的颜色
     *
     * @param alpha 透明度
     */
    private void setColorChange(float alpha) {
        titleTv.setTextColor(Color.argb((int) (alpha * 255), 255, 255, 255));
        topBarBgRl.setBackgroundColor(Color.argb((int) (alpha * 255), 55, 154, 246));
        statusBarView.setBackgroundColor(Color.argb((int) (alpha * 255), 55, 154, 246));
        searchIv.setAlpha(alpha);
    }

    /**
     * 计算高度差 获取透明度
     *
     * @param min
     * @param max
     * @return
     */
    public float getScaleDistance(float min, float max) {
        int position = 0;
        View firstVisibleChildView = layoutManager.findViewByPosition(position);
        if (firstVisibleChildView == null) return 1;
        return (firstVisibleChildView.getHeight() - firstVisibleChildView.getBottom() - min) / (max - min);
    }

    private View getBannerView() {
        View view = View.inflate(getActivity(), R.layout.adapter_item_header_banner, null);
        bgaBanner = view.findViewById(R.id.banner);
        bgaBanner.setVisibility(View.INVISIBLE);
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
        bgaBanner.setVisibility(View.VISIBLE);
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
    public void collectSuccess(int position, String msg) {
        datasEntities.get(position).setCollect(true);
        adapter.notifyItemChanged(position + 1, 1);
        showToast(msg);
    }

    @Override
    public void cancelCollectSuccess(int position, String msg) {
        datasEntities.get(position).setCollect(false);
        adapter.notifyItemChanged(position + 1, 1);
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
        mPresenter.getArticleList(page);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ARouterHelper.gotoWebViewActivity(datasEntities.get(position).getTitle(), datasEntities.get(position).getLink());
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
        BannerBean bannerBean = (BannerBean) model;
        ARouterHelper.gotoWebViewActivity(bannerBean.getTitle(), bannerBean.getUrl());
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (datasEntities.get(position).isCollect()) {
            mPresenter.cancelCollectArticleById(datasEntities.get(position).getId(), position);
        } else {
            mPresenter.collectArticleById(datasEntities.get(position).getId(), position);
        }
    }


    @OnClick(R.id.search_iv)
    public void onViewClicked() {
        ARouterHelper.gotoSearchActivity();
    }
}
