package com.allen.wanandroid.ui.collect;

import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.allen.wanandroid.bean.CollectBean;
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
public class CollectPresenter extends BaseMvpPresenter<CollectView> {

    private ApiModel apiModel;

    public CollectPresenter() {
        this.apiModel = new ApiModel();
    }


    public void getUserCollectList(int page) {
        mView.showLoading();
        apiModel.getUserCollectList(page, new DataObserver<CollectBean>() {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(CollectBean data) {
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


    public void cancelUserCollectArticleById(int id, int originId, final int position) {
        apiModel.cancelUserCollectArticleById(id, originId, new DataObserver<String>(mView.getLoadingDialog()) {
            @Override
            protected void onError(String errorMsg) {
            }

            @Override
            protected void onSuccess(String data) {
                mView.cancelCollectSuccess(position, "收藏已取消");
            }
        });
    }

}
