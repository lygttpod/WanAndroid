package com.allen.wanandroid.ui.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.allen.wanandroid.bean.HotBean;
import com.allen.wanandroid.constant.ARouterPath;
import com.allen.wanandroid.constant.BundleKey;
import com.allen.wanandroid.ui.view.SearchView;
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

    public void gotoWebSiteDetail(String title,String url){
        ARouter
                .getInstance()
                .build(ARouterPath.webViewPath)
                .withString(BundleKey.TITLE,title)
                .withString(BundleKey.URL,url)
                .navigation();
    }

    public void gotoHotDetail(String title,int id){
        ARouter
                .getInstance()
                .build(ARouterPath.articleListAcPath)
                .withString(BundleKey.TITLE,title)
                .withInt(BundleKey.ID,id)
                .navigation();
    }
    public void getHotSearchData(){
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

    public void getWebSiteData(){
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
