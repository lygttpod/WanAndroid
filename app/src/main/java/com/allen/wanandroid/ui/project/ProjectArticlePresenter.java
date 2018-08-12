package com.allen.wanandroid.ui.project;

import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.allen.wanandroid.bean.HomeBean;
import com.library.base.mvp.BaseMvpPresenter;


/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class ProjectArticlePresenter extends BaseMvpPresenter<ProjectArticleView> {

    private ApiModel apiModel;

    public ProjectArticlePresenter() {
        this.apiModel = new ApiModel();
    }


    public void collectArticleById(int id, final int position) {
        apiModel.collectArticleById(id, mView.getLoadingDialog(), new DataObserver<String>() {
            @Override
            protected void onError(String errorMsg) {
            }

            @Override
            protected void onSuccess(String data) {
                mView.collectSuccess(position, "收藏成功");
            }
        });
    }

    public void cancelCollectArticleById(int id, final int position) {
        apiModel.cancelCollectArticleById(id, mView.getLoadingDialog(), new DataObserver<String>() {
            @Override
            protected void onError(String errorMsg) {
            }

            @Override
            protected void onSuccess(String data) {
                mView.cancelCollectSuccess(position, "收藏已取消");
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
                    mView.showNewArticleList(data.getDatas());
                    mView.enableLoadMore(!data.isOver());
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
