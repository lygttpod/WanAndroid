package com.allen.quickbuildandroid.api;

import com.allen.library.RxHttpUtils;
import com.allen.library.bean.BaseData;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.DataObserver;
import com.allen.quickbuildandroid.bean.BannerBean;
import com.allen.quickbuildandroid.bean.HomeBean;

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

    public void getBanner(DataObserver<List<BannerBean>> observer) {
        RxHttpUtils.createApi(Apiservice.class)
                .getBanner()
                .compose(Transformer.<BaseData<List<BannerBean>>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }
}
