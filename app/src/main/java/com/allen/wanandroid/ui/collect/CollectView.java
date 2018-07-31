package com.allen.wanandroid.ui.collect;

import com.allen.wanandroid.bean.CollectBean;
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
public interface CollectView extends IBaseMvpView {

     void showNewArticleList(List<CollectBean.DatasBean> list);
     void showMoreArticleList(List<CollectBean.DatasBean> list);
     void cancelCollectSuccess(int position, String msg);

     void loadMoreComplete();
     void loadMoreEnd();

}
