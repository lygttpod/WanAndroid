package com.allen.wanandroid.ui.search;

import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.allen.wanandroid.arouter.ARouterHelper;
import com.allen.wanandroid.bean.HotBean;
import com.library.base.mvp.BaseMvpPresenter;

import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/27
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class SearchPresenter extends BaseMvpPresenter<SearchView> {

    private ApiModel apiModel;

    public SearchPresenter() {
        this.apiModel = new ApiModel();
    }

    public void gotoWebSiteDetail(String title, String url) {
        ARouterHelper.gotoWebViewActivity(title, url);
    }

    public void gotoSearchResult(String title, int id) {
        ARouterHelper.gotoSearchResult(title, id);
    }

    public void getHotSearchData() {
        apiModel.getHotSearchData(new DataObserver<List<HotBean>>() {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(List<HotBean> data) {
                mView.hideLoading();
                mView.setHotSearchData(data);
            }
        });
    }

    public void getWebSiteData() {
        apiModel.getWebSiteData(new DataObserver<List<HotBean>>() {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(List<HotBean> data) {
                mView.hideLoading();
                mView.setWebSiteData(data);
            }
        });
    }
}
