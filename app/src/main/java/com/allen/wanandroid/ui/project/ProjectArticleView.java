package com.allen.wanandroid.ui.project;

import com.allen.wanandroid.bean.HomeBean;
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
public interface ProjectArticleView extends IBaseMvpView {

     void showNewArticleList(List<HomeBean.DatasEntity> list);
     void showMoreArticleList(List<HomeBean.DatasEntity> list);
     void collectSuccess(int position, String msg);
     void cancelCollectSuccess(int position, String msg);

     void loadMoreComplete();
     void loadMoreEnd();

}
