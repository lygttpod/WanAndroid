package com.allen.wanandroid.ui.article;

import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.HomeBean;
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
public class ArticlePresenter extends BaseMvpPresenter<ArticleView> {

    private ApiModel apiModel;

    public ArticlePresenter() {
        this.apiModel = new ApiModel();
    }


    public void getBanner() {
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


    public void getArticleList(int page) {
        apiModel.getHomeArticleList(page, new DataObserver<HomeBean>() {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(HomeBean data) {
                mView.hideLoading();
                if (data.getCurPage() == 1) {
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    }
                    mView.showNewArticleList(data.getDatas());
                } else {
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    } else {
                        mView.loadMoreComplete();
                    }
                    mView.showMoreArticleList(data.getDatas());
                }
            }
        });
    }

    public void getUserCollectList(int page) {
        apiModel.getUserCollectList(page, new DataObserver<HomeBean>(mView.getLoadingDialog()) {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(HomeBean data) {
                mView.hideLoading();
                if (data.getCurPage() == 1) {
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    }
                    mView.showNewArticleList(data.getDatas());
                } else {
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    } else {
                        mView.loadMoreComplete();
                    }
                    mView.showMoreArticleList(data.getDatas());
                }
            }
        });
    }

    public void getCategoryArticleListWithId(int page, int id) {
        apiModel.getCategoryArticleListWithId(page, id, new DataObserver<HomeBean>() {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(HomeBean data) {
                mView.hideLoading();
                if (data.getCurPage() == 1) {
                    mView.showNewArticleList(data.getDatas());
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    }
                } else {
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    } else {
                        mView.loadMoreComplete();
                    }
                    mView.showMoreArticleList(data.getDatas());
                }
            }
        });
    }


    public void getProjectArticleListWithId(int page, int id) {
        apiModel.getProjectArticleListWithId(page, id, new DataObserver<HomeBean>() {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(HomeBean data) {
                mView.hideLoading();
                if (data.getCurPage() == 1) {
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    }
                    mView.showNewArticleList(data.getDatas());
                } else {
                    if (data.isOver()) {
                        mView.loadMoreEnd();
                    } else {
                        mView.loadMoreComplete();
                    }
                    mView.showMoreArticleList(data.getDatas());
                }
            }
        });
    }

}
