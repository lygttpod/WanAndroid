package com.allen.wanandroid.ui.presenter;

import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.ui.view.CategoryHomeView;
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
        apiModel.getCategoryList(new DataObserver<List<CategoryBean>>(mView.getLoadingDialog()) {
            @Override
            protected void onError(String errorMsg) {

            }

            @Override
            protected void onSuccess(List<CategoryBean> data) {
                mView.showCategoryList(data);
            }
        });
    }
}
