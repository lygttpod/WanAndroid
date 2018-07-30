package com.allen.wanandroid.ui.project;

import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.api.ApiModel;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.ui.project.ProjectHomeView;
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
public class ProjectHomePresenter extends BaseMvpPresenter<ProjectHomeView> {

    private ApiModel apiModel;

    public ProjectHomePresenter() {
        apiModel = new ApiModel();
    }

    public void getProjectTabData(){
        apiModel.getProjectTabData(new DataObserver<List<CategoryBean>>() {
            @Override
            protected void onError(String errorMsg) {

            }

            @Override
            protected void onSuccess(List<CategoryBean> data) {
                mView.setTabData(data);
            }
        });
    }
}
