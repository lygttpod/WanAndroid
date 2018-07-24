package com.allen.wanandroid.api;

import com.allen.library.RxHttpUtils;
import com.allen.library.bean.BaseData;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.bean.HomeBean;

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
public class ApiModel {

    public void getHomeArticleList(int page, DataObserver<HomeBean> observer) {
        RxHttpUtils.createApi(Apiservice.class)
                .getHomeArticleList(page)
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }
    public void getHomeArticleListWithId(int page,int id, DataObserver<HomeBean> observer) {
        RxHttpUtils.createApi(Apiservice.class)
                .getHomeArticleListWithId(page,id)
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }

    public void getBanner(DataObserver<List<BannerBean>> observer) {
        RxHttpUtils.createApi(Apiservice.class)
                .getBanner()
                .compose(Transformer.<BaseData<List<BannerBean>>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }

    public void getCategoryList(DataObserver<List<CategoryBean>> observer) {
        RxHttpUtils.createApi(Apiservice.class)
                .getCategoryList()
                .compose(Transformer.<BaseData<List<CategoryBean>>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }
}
