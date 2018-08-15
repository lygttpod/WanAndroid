package com.allen.common.arouter;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    : 页面路径
 *      version : 1.0
 * </pre>
 */
public interface ARouterPath {
    String mainPath = "/application/main/activity";

    String moduleArticleHomeFragmentPath = "/module_article/home/fragment";

    String moduleArticleWebViewActivityPath = "/module_article/details/webView/activity";
    String moduleArticleCategoryFragmentPath = "/module_article/category/fragment";
    String moduleArticleTabViewPagerActivityPath = "/module_article/tab_viewpager/activity";
    String moduleArticleArticleListFrPath = "/module_article/list/fragment";

    String moduleArticleUserCollectActivityPath = "/module_article/collect/activity";
    String moduleArticleSearchActivityPath = "/module_article/search/activity";
    String moduleArticleSearchResultFragmentPath = "/module_article/search/result/fragment";

    String moduleLoginLoginActivityPath = "/module_login/login/activity";

    String moduleUserMineFragmentPath = "/module_user/mine/fragment";
    String moduleUserAboutActivityPath = "/module_user/about/activity";
    String moduleUserSettingActivityPath = "/module_user/setting/activity";
}
