package com.allen.quickbuildandroid.home;

import com.allen.library.observer.DataObserver;
import com.allen.quickbuildandroid.api.ApiModel;
import com.allen.quickbuildandroid.bean.BannerBean;
import com.allen.quickbuildandroid.bean.HomeBean;
import com.library.base.mvp.BaseMvpPresenter;

import java.util.List;


/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class HomePresenter extends BaseMvpPresenter<HomeView>{

    private ApiModel apiModel;

    public HomePresenter() {
        this.apiModel = new ApiModel();
    }

    public void getArticleList(int page){
        apiModel.getHomeArticleList(page, new DataObserver<HomeBean>(mView.getLoadingDialog()) {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(HomeBean data) {
                mView.hideLoading();
                if (data.getCurPage()==1){
                    mView.showNewArticleList(data.getDatas());
                }else {
                    if (data.isOver()){
                        mView.loadMoreEnd();
                    }else {
                        mView.loadMoreComplete();
                    }
                    mView.showMoreArticleList(data.getDatas());
                }
            }
        });
    }

    public void getBanner(){
        apiModel.getBanner(new DataObserver<List<BannerBean>>() {
            @Override
            protected void onError(String errorMsg) {

            }

            @Override
            protected void onSuccess(List<BannerBean> data) {
                mView.showBanner(data);
            }
        });
    }
}
