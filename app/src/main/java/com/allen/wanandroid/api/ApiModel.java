package com.allen.wanandroid.api;

import com.allen.library.RxHttpUtils;
import com.allen.library.bean.BaseData;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.bean.HomeBean;
import com.allen.wanandroid.bean.UserBean;

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
        RxHttpUtils.createApi(ApiService.class)
                .getHomeArticleList(page)
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }

    public void getHomeArticleListWithId(int page, int id, DataObserver<HomeBean> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getHomeArticleListWithId(page, id)
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }

    public void getBanner(DataObserver<List<BannerBean>> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getBanner()
                .compose(Transformer.<BaseData<List<BannerBean>>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }

    public void getCategoryList(DataObserver<List<CategoryBean>> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getCategoryList()
                .compose(Transformer.<BaseData<List<CategoryBean>>>switchSchedulers(observer.getProgressDialog()))
                .subscribe(observer);
    }

    public void login(String userName, String psd, DataObserver<UserBean> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .login(userName, psd)
                .compose(Transformer.<BaseData<UserBean>>switchSchedulers())
                .subscribe(observer);

    }

    public void register(String userName, String psd, String rePsd, DataObserver<UserBean> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .register(userName, psd, rePsd)
                .compose(Transformer.<BaseData<UserBean>>switchSchedulers())
                .subscribe(observer);

    }
}
