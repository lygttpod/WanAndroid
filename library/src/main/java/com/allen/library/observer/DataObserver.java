package com.allen.library.observer;

import android.app.Dialog;
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
 *         <p>
 *         针对特定格式的时候设置的通用的Observer
 *         用户可以根据自己需求自定义自己的类继承BaseDataObserver<T>即可
 *         适用于
 *         {
 *         "code":200,
 *         "msg":"成功"
 *         "data":{
 *         "userName":"test"
 *         "token":"abcdefg123456789"
 *         "uid":"1"}
 *         }
 */

public abstract class DataObserver<T> extends BaseDataObserver<T> {

    private Dialog mProgressDialog;

    public Dialog getProgressDialog() {
        return mProgressDialog;
    }

    public DataObserver() {
    }

    public DataObserver(Dialog progressDialog) {
        mProgressDialog = progressDialog;
    }

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
        //RxHttpUtils.addDisposable(d);
        RxHttpUtils.addToCompositeDisposable(d);
    }

    @Override
    public void doOnError(String errorMsg) {
        dismissLoading();
        if (!isHideToast()&& !TextUtils.isEmpty(errorMsg)) {
            ToastUtils.showToast(errorMsg);
        }
        onError(errorMsg);
    }

    @Override
    public void doOnNext(BaseData<T> data) {
        onSuccess(data.getData());
        //可以根据需求对code统一处理
        if (data.getErrorCode()>=0){
            onSuccess(data.getData());
        }else {
            onError(data.getErrorMsg());
        }
    }

    @Override
    public void doOnCompleted() {
        dismissLoading();
    }

    /**
     * 隐藏loading对话框
     */
    private void dismissLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
