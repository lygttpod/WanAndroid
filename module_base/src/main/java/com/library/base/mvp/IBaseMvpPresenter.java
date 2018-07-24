package com.library.base.mvp;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/05
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public interface IBaseMvpPresenter<V extends IBaseMvpView> {

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    void attachView(V view);

    /**
     * 解除绑定view，一般在onDestroy中调用
     */

    void detachView();

    /**
     * View是否绑定
     *
     * @return boolean
     */
    boolean isViewAttached();

}
