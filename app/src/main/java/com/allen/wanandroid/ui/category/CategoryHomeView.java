package com.allen.wanandroid.ui.category;

import com.allen.common.bean.CategoryBean;
import com.library.base.mvp.IBaseMvpView;

import java.util.List;


/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/23
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface CategoryHomeView extends IBaseMvpView {
    void showCategoryList(List<CategoryBean> list);
}
