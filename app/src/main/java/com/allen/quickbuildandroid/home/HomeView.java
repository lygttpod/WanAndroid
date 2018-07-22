package com.allen.quickbuildandroid.home;

import android.app.Dialog;

import com.allen.quickbuildandroid.bean.BannerBean;
import com.allen.quickbuildandroid.bean.HomeBean;
import com.library.base.mvp.IBaseMvpView;

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
public interface HomeView extends IBaseMvpView {

     void showBanner(List<BannerBean> list);
     void showNewArticleList(List<HomeBean.DatasEntity> list);
     void showMoreArticleList(List<HomeBean.DatasEntity> list);

     void loadMoreComplete();
     void loadMoreEnd();
     Dialog getLoadingDialog();

}
