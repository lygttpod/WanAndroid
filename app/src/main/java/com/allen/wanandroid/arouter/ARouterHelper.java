package com.allen.wanandroid.arouter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.wanandroid.bean.CategoryBean;
import com.allen.wanandroid.constant.BundleKey;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/01
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class ARouterHelper {
    public static void gotoSearchActivity() {
        ARouter
                .getInstance()
                .build(ARouterPath.searchActPath)
                .navigation();
    }

    public static void gotoWebViewActivity(String title, String url) {
        ARouter
                .getInstance()
                .build(ARouterPath.webViewPath)
                .withString(BundleKey.TITLE, title)
                .withString(BundleKey.URL, url)
                .navigation();
    }


    public static void gotoSearchResult(String title) {
        ARouter
                .getInstance()
                .build(ARouterPath.searchResultFrgPath)
                .withString(BundleKey.TITLE, title)
                .navigation();
    }

    public static void gotoCollectList() {
        ARouter
                .getInstance()
                .build(ARouterPath.userCollectAcPath)
                .navigation();
    }

    public static void startArticleTabViewPagerAct(CategoryBean bean, int index) {
        ARouter
                .getInstance()
                .build(ARouterPath.articleTabViewPagerAcPath)
                .withInt("index", index)
                .withParcelable("category", bean)
                .navigation();
    }

    public static void login() {
        ARouter
                .getInstance()
                .build(ARouterPath.loginPath)
                .navigation();

    }

    public static void gotoMain() {
        ARouter
                .getInstance()
                .build(ARouterPath.mainPath)
                .navigation();

    }

    public static void about() {
        ARouter
                .getInstance()
                .build(ARouterPath.aboutActPath)
                .navigation();

    }

    public static void setting() {
        ARouter
                .getInstance()
                .build(ARouterPath.settingActPath)
                .navigation();

    }

}
