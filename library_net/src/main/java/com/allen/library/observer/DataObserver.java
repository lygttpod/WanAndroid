package com.allen.library.observer;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.allen.library.RxHttpUtils;
import com.allen.library.base.BaseDataObserver;
import com.allen.library.bean.BaseData;
import com.allen.library.utils.ToastUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by Allen on 2017/10/31.
 *
 * @author Allen
 * <p>
 * 针对特定格式的时候设置的通用的Observer
 * 用户可以根据自己需求自定义自己的类继承BaseDataObserver<T>即可
 * 适用于
 * {
 * "code":200,
 * "msg":"成功"
 * "data":{
 * "userName":"test"
 * "token":"abcdefg123456789"
 * "uid":"1"}
 * }
 */

public abstract class DataObserver<T> extends BaseDataObserver<T> {

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param data 结果
     */
    protected abstract void onSuccess(T data);

    @Override
    public void doOnSubscribe(Disposable d) {
    }

    @Override
    public void doOnError(String errorMsg) {
        if (!isHideToast() && !TextUtils.isEmpty(errorMsg)) {
            ToastUtils.showToast(errorMsg);
        }
        onError(errorMsg);
    }

    @Override
    public void doOnNext(BaseData<T> data) {
        //可以根据需求对code统一处理
        if (data.getErrorCode() >= 0) {
            onSuccess(data.getData());
        } else {
            doOnError(data.getErrorMsg());
            if (data.getErrorMsg().contains("登录")) {
                Intent intent = new Intent("com.allen.wan_android.need_login_action");
                //android8.0之后对静态广播做了限制（为了省电），需要加上setComponent这行就没问题了
                //ComponentName的两个参数，第一个是应用包名(网上很多说是自定义广播的包名，其实应该是应用的包名，查看源码可以的到验证)，第二个是自定义广播的全路径
                intent.setComponent(new ComponentName("com.allen.wanandroid", "com.allen.wanandroid.broadcast.LoginBroadcastReceiver"));
                RxHttpUtils.getContext().sendBroadcast(intent);
            }
        }

    }

    @Override
    public void doOnCompleted() {
    }


}
