package com.allen.wanandroid.ui.category;

import com.allen.common.bean.CategoryBean;
import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.library.base.mvp.BaseMvpPresenter;

import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/23
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class CategoryHomePresenter extends BaseMvpPresenter<CategoryHomeView> {
    private ApiModel apiModel;

    public CategoryHomePresenter() {
        this.apiModel = new ApiModel();
    }

    public void getCategoryData(){
        apiModel.getCategoryList(new DataObserver<List<CategoryBean>>() {
            @Override
            protected void onError(String errorMsg) {
                mView.hideLoading();
            }

            @Override
            protected void onSuccess(List<CategoryBean> data) {
                mView.showCategoryList(data);
                mView.hideLoading();
            }
        });
    }
}
