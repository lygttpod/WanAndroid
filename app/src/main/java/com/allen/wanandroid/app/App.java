package com.allen.wanandroid.app;

import android.app.Application;
import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.config.OkHttpConfig;

import okhttp3.OkHttpClient;

/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/07/21
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class App extends Application {
    public static Context getContext() {
        return context;
    }

    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initHttp();
    }

    private void initHttp() {
        OkHttpClient okHttpClient = new OkHttpConfig
                .Builder()
                //全局的请求头信息
                .setHeaders(null)
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
                .setCache(true)
                //全局持久话cookie,保存本地每次都会携带在header中（默认false）
                .setSaveCookie(true)
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                //.setAddInterceptor(null)
                //全局ssl证书认证
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                //.setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setDebug(true)
                .build();

        RxHttpUtils
                .getInstance()
                .init(this)
                .config()
                //配置全局baseUrl
                .setBaseUrl("http://www.wanandroid.com/")
                //开启全局配置
                .setOkClient(okHttpClient);
    }
}
