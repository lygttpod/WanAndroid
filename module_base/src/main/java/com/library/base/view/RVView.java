package com.library.base.view;

import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/30
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface RVView<T> {
    void showNewData(List<T> list);
    void showMoreData(List<T> list);

    void loadMoreComplete();
    void loadMoreEnd();
}
