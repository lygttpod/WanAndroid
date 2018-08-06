package com.allen.wanandroid.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.RxHttpUtils;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/26
 *      desc    : 登录拦截器
 *      version : 1.0
 * </pre>
 */
@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        if (postcard.getPath().equals(ARouterPath.userCollectAcPath)) {
            if (RxHttpUtils.getAllCookie().size() > 0) {
                callback.onContinue(postcard);
            } else {
                callback.onInterrupt(null);
                ARouter.getInstance().build(ARouterPath.loginPath).navigation();
            }
        } else {
            callback.onContinue(postcard);
        }

    }

    @Override
    public void init(Context context) {

    }
}
