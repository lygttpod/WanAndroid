package com.allen.wanandroid.api;

import com.allen.common.bean.BannerBean;
import com.allen.common.bean.CategoryBean;
import com.allen.common.bean.CollectBean;
import com.allen.common.bean.HomeBean;
import com.allen.common.bean.HotBean;
import com.allen.common.bean.UserBean;
import com.allen.library.bean.BaseData;

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
 *      desc    : 请求接口
 *      version : 1.0
 * </pre>
 */
public interface ApiService {

    /**
     * 获取首页文章列表
     *
     * @param page 页码
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseData<HomeBean>> getHomeArticleList(@Path("page") int page);

    /**
     * 根据ID获取文章列表
     *
     * @param page 页码
     * @param id   id
     * @return
     */
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
    Observable<BaseData<String>> cancelUserCollectArticleById(@Path("id") int id, @Field("originId") int originId);


    /**
     * 获取banner数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseData<List<BannerBean>>> getBanner();

    /**
     * 获取知识体系分类数据
     *
     * @return
     */
    @GET("tree/json")
    Observable<BaseData<List<CategoryBean>>> getCategoryList();

    /**
     * 登录
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    @POST("user/login")
    Observable<BaseData<UserBean>> login(@Query("username") String userName, @Query("password") String passWord);

    /**
     * 用户注册
     *
     * @param userName    用户名
     * @param passWord    密码
     * @param repPassWord 核对密码
     * @return
     */
    @POST("user/register")
    Observable<BaseData<UserBean>> register(@Query("username") String userName, @Query("password") String passWord, @Query("repassword") String repPassWord);

    /**
     * 热搜
     *
     * @return
     */
    @GET("hotkey/json")
    Observable<BaseData<List<HotBean>>> getHotSearchData();

    /**
     * 常用站点
     *
     * @return
     */
    @GET("friend/json")
    Observable<BaseData<List<HotBean>>> getWebSiteData();

    /**
     * 关键字搜索
     *
     * @param page    页码
     * @param keyword 关键字
     * @return 列表
     */
    @POST("article/query/{page}/json")
    Observable<BaseData<HomeBean>> getSearchListByKeyWord(@Path("page") int page, @Query("k") String keyword);

    /**
     * 项目分类
     *
     * @return 列表
     */
    @GET("project/tree/json")
    Observable<BaseData<List<CategoryBean>>> getProjectTabData();

    /**
     * 根据项目分类id获取项目列表
     *
     * @param page
     * @param id
     * @return
     */
    @GET("project/list/{page}/json")
    Observable<BaseData<HomeBean>> getProjectArticleListWithId(@Path("page") int page, @Query("cid") int id);

}
