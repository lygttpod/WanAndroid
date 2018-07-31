package com.allen.wanandroid.api;

import com.allen.library.bean.BaseData;
import com.allen.wanandroid.bean.BannerBean;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.bean.CollectBean;
import com.allen.wanandroid.bean.HomeBean;
import com.allen.wanandroid.bean.HotBean;
import com.allen.wanandroid.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface ApiService {

    /**
     * 获取首页文章列表
     *
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseData<HomeBean>> getHomeArticleList(@Path("page") int page);

    @GET("article/list/{page}/json")
    Observable<BaseData<HomeBean>> getHomeArticleListWithId(@Path("page") int page, @Query("cid") int id);

    /**
     * 收藏列表
     *
     * @param page 页码
     * @return 列表
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseData<CollectBean>> getUserCollectList(@Path("page") int page);

    /**
     * 收藏站内文章
     *
     * @param id 文章id
     * @return 结果
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseData<String>> collectArticleById(@Path("id") int id);


    /**
     * 取消文章列表的收藏
     *
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseData<String>> cancelCollectArticleById(@Path("id") int id);

    /**
     * 取消用户收藏页面的收藏
     *
     * @param id 文章id
     * @return
     */
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    Observable<BaseData<String>> cancelUserCollectArticleById(@Path("id") int id,@Field("originId") int originId);


    @GET("banner/json")
    Observable<BaseData<List<BannerBean>>> getBanner();

    @GET("tree/json")
    Observable<BaseData<List<CategoryBean>>> getCategoryList();

    @POST("user/login")
    Observable<BaseData<UserBean>> login(@Query("username") String userName, @Query("password") String passWord);

    @POST("user/register")
    Observable<BaseData<UserBean>> register(@Query("username") String userName, @Query("password") String passWord, @Query("repassword") String repPassWord);

    @GET("hotkey/json")
    Observable<BaseData<List<HotBean>>> getHotSearchData();

    @GET("friend/json")
    Observable<BaseData<List<HotBean>>> getWebSiteData();

    /**
     * 关键字搜索
     *
     * @param page    页码
     * @param keyword 关键字
     * @return 列表
     */
    @GET("article/query/{page}/json")
    Observable<BaseData<HomeBean>> getSearchListByKeyWord(@Path("page") int page, @Query("k") String keyword);

    /**
     * 项目分类
     *
     * @return 列表
     */
    @GET("project/tree/json")
    Observable<BaseData<List<CategoryBean>>> getProjectTabData();

    @GET("project/list/{page}/json")
    Observable<BaseData<HomeBean>> getProjectArticleListWithId(@Path("page") int page, @Query("cid") int id);

}
