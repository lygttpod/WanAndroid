package com.allen.wanandroid.ui.search;

import com.allen.wanandroid.bean.HotBean;
import com.library.base.mvp.IBaseMvpView;

import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/27
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface SearchView extends IBaseMvpView {

    void setHotSearchData(List<HotBean> hotSearchData);
    void setWebSiteData(List<HotBean> webSiteData);
}
