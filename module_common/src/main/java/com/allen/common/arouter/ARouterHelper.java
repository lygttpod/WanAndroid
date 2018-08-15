package com.allen.common.arouter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.common.bean.CategoryBean;
import com.allen.common.constant.BundleKey;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/08/01
 *      desc    : 页面跳转
 *      version : 1.0
 * </pre>
 */
public class ARouterHelper {
    public static void gotoSearchActivity() {
        ARouter
                .getInstance()
                .build(ARouterPath.moduleArticleSearchActivityPath)
                .navigation();
    }

    public static void gotoWebViewActivity(String title, String url) {
        ARouter
                .getInstance()
                .build(ARouterPath.moduleArticleWebViewActivityPath)
                .withString(BundleKey.TITLE, title)
                .withString(BundleKey.URL, url)
                .navigation();
    }


    public static void gotoSearchResult(String title) {
        ARouter
                .getInstance()
                .build(ARouterPath.moduleArticleSearchResultFragmentPath)
                .withString(BundleKey.TITLE, title)
                .navigation();
    }

    public static void gotoCollectList() {
        ARouter
                .getInstance()
                .build(ARouterPath.moduleArticleUserCollectActivityPath)
                .navigation();
    }

    public static void startArticleTabViewPagerAct(CategoryBean bean, int index) {
        ARouter
                .getInstance()
                .build(ARouterPath.moduleArticleTabViewPagerActivityPath)
                .withInt("index", index)
                .withParcelable("category", bean)
                .navigation();
    }

    public static void login() {
        ARouter
                .getInstance()
                .build(ARouterPath.moduleLoginLoginActivityPath)
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
                .build(ARouterPath.moduleUserAboutActivityPath)
                .navigation();

    }

    public static void setting() {
        ARouter
                .getInstance()
                .build(ARouterPath.moduleUserSettingActivityPath)
                .navigation();

    }

}
