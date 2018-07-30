package com.allen.wanandroid.ui.project;

import com.allen.wanandroid.bean.CategoryBean;
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
public interface ProjectHomeView extends IBaseMvpView {

    void setTabData(List<CategoryBean> tabs);
}
