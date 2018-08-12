package com.allen.wanandroid.api;

import android.app.Dialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.bean.BaseData;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.DataObserver;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.bean.CollectBean;
import com.allen.wanandroid.bean.HomeBean;
import com.allen.wanandroid.bean.HotBean;
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
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers())
                .subscribe(observer);
    }

    public void getCategoryArticleListWithId(int page, int id, DataObserver<HomeBean> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getHomeArticleListWithId(page, id)
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers())
                .subscribe(observer);
    }

    public void getBanner(DataObserver<List<BannerBean>> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getBanner()
                .compose(Transformer.<BaseData<List<BannerBean>>>switchSchedulers())
                .subscribe(observer);
    }

    public void getCategoryList(DataObserver<List<CategoryBean>> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getCategoryList()
                .compose(Transformer.<BaseData<List<CategoryBean>>>switchSchedulers())
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

    public void getUserCollectList(int page, DataObserver<CollectBean> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getUserCollectList(page)
                .compose(Transformer.<BaseData<CollectBean>>switchSchedulers())
                .subscribe(observer);
    }

    public void collectArticleById(int id, Dialog loading,DataObserver<String> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .collectArticleById(id)
                .compose(Transformer.<BaseData<String>>switchSchedulers(loading))
                .subscribe(observer);
    }

    public void cancelCollectArticleById(int id, Dialog loading,DataObserver<String> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .cancelCollectArticleById(id)
                .compose(Transformer.<BaseData<String>>switchSchedulers(loading))
                .subscribe(observer);
    }

    public void cancelUserCollectArticleById(int id, int originId,Dialog loading, DataObserver<String> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .cancelUserCollectArticleById(id, originId)
                .compose(Transformer.<BaseData<String>>switchSchedulers(loading))
                .subscribe(observer);
    }

    public void getHotSearchData(DataObserver<List<HotBean>> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getHotSearchData()
                .compose(Transformer.<BaseData<List<HotBean>>>switchSchedulers())
                .subscribe(observer);
    }

    public void getSearchListByKeyWord(int page, String k, DataObserver<HomeBean> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getSearchListByKeyWord(page, k)
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers())
                .subscribe(observer);
    }

    public void getWebSiteData(DataObserver<List<HotBean>> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getWebSiteData()
                .compose(Transformer.<BaseData<List<HotBean>>>switchSchedulers())
                .subscribe(observer);
    }

    public void getProjectTabData(DataObserver<List<CategoryBean>> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getProjectTabData()
                .compose(Transformer.<BaseData<List<CategoryBean>>>switchSchedulers())
                .subscribe(observer);
    }

    public void getProjectArticleListWithId(int page, int id, DataObserver<HomeBean> observer) {
        RxHttpUtils.createApi(ApiService.class)
                .getProjectArticleListWithId(page, id)
                .compose(Transformer.<BaseData<HomeBean>>switchSchedulers())
                .subscribe(observer);
    }


}
